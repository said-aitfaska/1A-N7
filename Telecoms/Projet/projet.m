Fe = 24000;
Rb = 3000;
Ts = 1 / Rb;   % pour les chaines ref et 1, pas la 2
Te = 1 / Fe;
Ns = Ts / Te;
N = 3000;

to_0 = 0;
to_1 = to_0 + Ns;
alpha_0 = 1;
alpha_1 = 0.5;

% message binaire aléatoire (à décommenter pour les questions concernées)
message = randi([0, 1], 1, N);

% % reponse impulsionelle (à décommenter pour les questions concernées)
%message = zeros(N, 1)';
%message(1) = 1;

% exemple sujet
%message = [1 1 1 0 0 1 0];
%N = 7;

%%%%%%%%%%%%%%%%%%%% PARTIE SANS BRUIT ET SANS CANAL %%%%%%%%%%%%%%%%%%%%%%

% mapping 
message_map = message * 2 - 1;

% modulateur
h = ones(1,Ns);

% suréchentillonage
signal_surech = kron(message_map, [1 zeros(1, Ns-1)]);

% filtrage de mise en forme
signal = filter(h, 1, signal_surech);

% reception
signal_reception = filter(h, 1, signal);
%tracage de reponse filtre de reception 
figure();
plot(signal_reception);
title("signal à la reception sans canal ");
xlabel('frequence en Hz');
ylabel('signal reception');


% diagramme de l'oeil
figure;
plot(reshape(signal_reception, Ns, length(signal_reception) / Ns));
title("diagramme de l'oeil");

% decisions (avec seuil = 0, donc on utilise la fonction sign())
n01 = 8;
message_reception = signal_reception(n01:Ns:end); % n01 déterminé visuelement
message_reception = sign(message_reception);

% demapping
reponse = (message_reception + 1) / 2;

% taux d'erreur
taux_erreur = sum(message ~= reponse) / N;

%%%%%%%%%%%%%%%%% INTRODUCTION DU CANAL %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% ajout du canal de propagation (on peut commenter cette partie)
h_c = zeros(length(signal), 1);
h_c(to_0 + 1) = alpha_0;
h_c(to_1 + 1) = alpha_1;

% on garde le signal sans filtre canal pour plus tard
signal_sans_canal = signal;

signal = filter(h_c, 1, signal);

% reception
signal_reception = filter(h, 1, signal);

% decisions (avec seuil = 0, donc on utilise la fonction sign())
n01 = 8;
message_reception = signal_reception(n01:Ns:end); % n01 déterminé visuelement
message_reception = sign(message_reception);

% demapping
reponse = (message_reception + 1) / 2;

% taux d'erreur
taux_erreur_canal = sum(message ~= reponse) / N;

% vérifications sans bruit
% forme du signal à la reception 
figure();
plot(signal_reception);
title("signal à la reception sans ajout de bruit");
xlabel('frequence en Hz');
ylabel('signal reception');
%tracer les constellations en sortie de reception
figure();
plot(real(signal_reception));
title('constellations en sortie de reception');


%%%%%%%%%%%%%%%%% INTRODUCTION DU BRUIT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

resultat_taux_erreur = zeros(11, 2);
resultat_taux_erreur_sans_canal = zeros(11, 2);
for Eb_No = 0:10   % en db
    
    alea = randn(1, length(signal));
    Puiss_sign = mean(abs(signal) .^ 2);
    Puiss_bruit = Puiss_sign * Ns  / (2 * log2(2) * 10 ^ (Eb_No / 10));
    Bruit_gauss = sqrt(Puiss_bruit) * alea;

    signal_bruit = signal + Bruit_gauss;
    signal_bruit_sans_canal = signal_sans_canal + Bruit_gauss;
     
    % reception
    signal_reception = filter(h, 1, signal_bruit);
    signal_reception_sans_canal = filter(h, 1, signal_bruit_sans_canal);

    % decisions (avec seuil = 0, donc on utilise la fonction sign())
    n01 = 8;
    message_reception = signal_reception(n01:Ns:end); % n01 déterminé visuelement
    message_reception = sign(message_reception);
    
    message_reception_sans_canal = signal_reception_sans_canal(n01:Ns:end); % n01 déterminé visuelement
    message_reception_sans_canal = sign(message_reception_sans_canal);

    % demapping
    reponse = (message_reception + 1) / 2;
    reponse_sans_canal = (message_reception_sans_canal + 1) / 2;

    % taux d'erreur
    taux_erreur_canal_bruit = sum(message ~= reponse) / N;
    taux_erreur_sans_canal_bruit = sum(message ~= reponse_sans_canal) / N;
    
    resultat_taux_erreur(Eb_No + 1, :) = [Eb_No, taux_erreur_canal_bruit]; 
    resultat_taux_erreur_sans_canal(Eb_No + 1, :) = [Eb_No, taux_erreur_sans_canal_bruit];
end

%tracer le TEB :
figure();
semilogy(resultat_taux_erreur(:,2),'g-');
grid;
xlabel('Eb/No');
ylabel('TEB');
title('TEB de la chaine etudie ');




% comparaison TEB théorique et TEB simulé
figure;
semilogy(resultat_taux_erreur(:, 2), 'r-');
hold on;
semilogy(qfunc(sqrt((2 * 10 .^ ([0 : 10] / 10)))),'g-');
grid;
title('Comparaison entre le TEB théorique et estimé');
legend('TEB estimé','TEB théorique')
xlabel('Eb/No');
ylabel('TEB');

% comparaison TEB avec et sans canal
figure;
semilogy(resultat_taux_erreur(:, 2), 'r-');
hold on;
semilogy(resultat_taux_erreur_sans_canal(:, 2), 'g-');
grid;
title('Comparaison TEB avec et sans canal');
legend('TEB avec canal','TEB sans canal')
xlabel('Eb/No');
ylabel('TEB');

%%%%%%%%%%%%%%%%%%%%%%%% Egalisateur %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Pour construire l'égalisateur
message_eg = zeros(N, 1)';
message_eg(1) = 1;

filtre_chaine = conv(h,h);
filtre_chaine = conv(filtre_chaine,h_c);
filtre_chaine = filtre_chaine(1:Ns:end);

% construire la matrice Z pour l'apprentisage
Z = zeros(N);
for i = 1:N
    Z(: , i) = [zeros(i-1, 1) ; (filtre_chaine(1:N-(i-1)))];
end

% calcul des coeficients
Y0 = zeros(N, 1);
Y0(1) = 1;
coeficients = pinv(Z) * Y0;

coeficients1 = flip(coeficients);

% construction de l'égalisateur
h_eg = coeficients';

% signal reception avec égalisateur
message_reception_g = signal_reception(n01:Ns:end); 
message_reception_g = filter(h_eg, 1, message_reception_g);
message_reception_g = sign(message_reception_g);

% demapping
reponse_g = (message_reception_g + 1) / 2;

% comparaison des reponses en fréquences
figure();
DSP_chaine = transpose(abs(fft(filtre_chaine)));
DSP_eg = abs(fft(h_eg));
DSP_chaine =DSP_chaine(1:length(DSP_eg));
plot(DSP_chaine);
hold on;
plot(DSP_eg);
plot(DSP_chaine .* DSP_eg);
legend('DSP_chaine','DSP_egalisateur','DSP_produit');
xlabel('frequence en Hz');
ylabel("Amplitude en dB");


% comparaison avec et sans l'égaliseur pour la réponse impulsionelle
figure();
plot(message_reception);
hold on;
plot(message_reception_g);
legend('sans égalisateur','avec égalisateur');
xlabel('temps en s');
ylabel('x(t)');
difference_egalisateur= sum(message_reception ~= message_reception_g) / N;

%% AJOUT DE BRUIT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

resultat_taux_erreur_egalisateur = zeros(11, 2);
for Eb_No = 0:10   % en db
 
    alea = randn(1, length(signal));
    Puiss_sign = mean(abs(signal) .^ 2);
    Puiss_bruit = Puiss_sign * Ns  / (2 * log2(2) * 10 ^ (Eb_No / 10));
    Bruit_gauss = sqrt(Puiss_bruit) * alea;

    signal_bruit_eg = signal + Bruit_gauss;

    % reception
    signal_reception_eg = filter(h, 1, signal_bruit_eg);

    % decisions (avec seuil = 0, donc on utilise la fonction sign())
    n01 = 8;
    message_reception_eg = signal_reception_eg(n01:Ns:end); 

    % signal bruit en  reception avec égalisateur
    message_reception_eg = filter(h_eg, 1, message_reception_eg);
    message_reception_eg = sign(message_reception_eg);

    %demapping
    reponse_eg = (message_reception_eg + 1) / 2;

    %Taux erreur
    taux_erreur_avec_egalisateur= sum(reponse_eg ~= message) / N;
    
    resultat_taux_erreur_egalisateur(Eb_No + 1, :) = [Eb_No, taux_erreur_avec_egalisateur];
end

% comparaison TEB avec et sans égalisation
figure;
semilogy(resultat_taux_erreur(:, 2), 'r-');
hold on;
semilogy(resultat_taux_erreur_egalisateur(:, 2),'g-');
grid;
title('comparaison TEB avec et sans égalisation');
legend('TEB sans égalisateur','TEB avec égalisateur')
xlabel('Eb/No');
ylabel('TEB');
