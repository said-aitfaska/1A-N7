%question 3.1.1 

load DonneesBinome6.mat
message = transpose(bits);
%message = randi([0,1],4);
%message = message(:)
Fe=48000;                  %Frequence d'echantillonnage 
Te=1/Fe;
Ts = 1/300;
Ns = round(Ts/Te);
F0 = 1180;
F1 = 980;
t = [0:Te:(Ns*length(message)-1)*Te];

NRZ = repmat(transpose(message), Ns, 1);
NRZ = transpose(reshape(NRZ, 1, []));

figure(1)
plot(t,NRZ)
xlabel('  Temps (s) ')
ylabel(' Niveau ')
title(' Tracage du signal NRZ')


%Question 3.1.1.3

TF_NRZ=abs(fft(NRZ)).^2;              % densite spectrale 
figure(2)
f = linspace(0,Fe,length(TF_NRZ));
DSP_NRZ = TF_NRZ.^2;
semilogy(f, DSP_NRZ)
xlabel(' frequence en (HZ) ');
ylabel( ' Amplitude du signal ');
title(' DSP de NRZ ');

%Question 3.1.2

x= ( 1-NRZ).*transpose(cos(2*pi*F0*t + rand*2*pi)) + NRZ .* transpose(cos(2*pi*F1*t + rand*2*pi));
x_1=  (1-NRZ).*transpose(cos(2*pi*F0*t)) + NRZ .* transpose(cos(2*pi*F1*t));
%1ere partie fsk 

%y = NRZ .* transpose(cos(2*pi*F1*t + rand*2*pi));
figure(3);
plot(t,x);
xlabel(' Temps (s) ');
ylabel(' Amplitude ');
title(' Representation du signal x(t) ');

%%%%%%%  Revoir comment calculer la dsp   %%%%%%%%%%

% Question 3.1.2.4
Z= abs(fft(x)).^2;
figure(4);
semilogy(f,Z);
ylabel(' |TF(x)|');
xlabel(' Frequence en (Hz) ');
title (' Representation de |TF(x)|');

%%%%% 3.2 

SNR_cible = 70 ;
P_x = mean(abs(x).^2);
P_b = P_x / 10 .^(SNR_cible/10);             % A DETAILLER les calculs  DANS LE RAPPORT !!!
sigma = sqrt(P_b);                            % le programme donne une valeur de sigma de 0.3975 

%sigma = 0.056;      % il faut trouver sigma , cette valeur semble bien a peu pres
bruit = sigma .* randn(1, Ns * length(message));
x_bruit = x + transpose(bruit) ;
x_bruit_1= x_1 + transpose(bruit);
figure(6);
plot(t,x_bruit);
xlabel(' Temps en (s) ');
ylabel(' Amplitude '); 


                                              %%% PARTIE DEMODULATION %%%%


%implantation du filtre passe bas

fc_filtre = 4000;
ordre = 31;
interval=[-(ordre-1)/2*Te:Te:(ordre-1)/2*Te];
filtre_bas = (2*fc_filtre/Fe)*sinc(2*fc_filtre*interval);
filtrage = filter(filtre_bas,1,x_bruit);           % le signal contenant le bruit qui faut filtrer !
figure(7)
plot(t,filtrage)
xlabel('Temps en (s)')
ylabel('Niveau du signal ')
title('Signal apres Filtrage passe-bas')

%implantation du filtre passe-haut 

filtre_haut = -1 * (2*fc_filtre/Fe)*sinc(2*fc_filtre*interval);

filtre_haut((ordre -1) / 2) = 1; % rajout du dirac

filtrage_haut = filter(filtre_haut,1,x_bruit);
figure(8)
plot(t,filtrage_haut)        
xlabel('Temps  en (s)')
ylabel('Niveau du signal ')
title('Signal apres Filtrage passe-haut')

                                         % Question 3.3.4 %
% TODO tracer la DSP du filtre également, puis, sur le même graphique le signal obtenu (filtrage) 
% Q 3.3.4.1  ce sont les traces dessus (filtre passe bas et haut)

% Q3.3.4.2 : tracage de densite spectrale et reponse freq. sur le meme graphe :
figure(9)
DSP_signal_module = abs(fft(x)).^2;         % on demande la dsp du signal x 
plot(f, DSP_signal_module)
hold on                        % permet de tracer sur le meme graphe 
plot(length(filtre_bas), abs(fft(filtre_bas)), 'r-')            % dans l'enonce on demande seulement celle du filtre passe bas mais filtrage c'est apres non? 
hold off;                        % avec plot(f,filtre_bas) on a un erreur de dimensions..
title('DSP de x_bruit et sa reponse frequentielle');
ylabel('amplitude du signal');
xlabel('Frequence en (HZ)');


% Question 3.3.5

% les collones de cettte matrice contiennent les échantillons de la
% tranche correspondante
tranches_signal = reshape(transpose(filtrage), Ns, length(message));

seuil = 0.1;   % choisit à partir d'observations sur le signal aprés filtrage
message_restitue = mean(tranches_signal.^2) > seuil;

% Question 3.3.5.2
%(à expliquer dans le rapport dans le rapport)


taux_erreur = mean(message ~= transpose(message_restitue));
    

% puis faire le test avec l'image    --- il faut telecharger les fichiers
% dans moodle pour tester l'image 

%reconstitution_image 
%code reconstitution_image 
%reconstitution_image(message_restitue)              % bits correspond au donnes.mat fourni pour tester 
%which reconstitution_image 

% Question 3.4

% donner les resultats dans le rapport



% SECTION 4

% expliquer dans le rapport le fonctionnement
% ce que j'ai compris :
% on garde notre le principe de la matrice tranches_signal, mais avant le
% filtrage
% puis on multiplie chaque tranche 2 fois, par cos(2piF0[0:Te:Ns*Te]) et par cos(2piF1[0:Te:Ns*Te])
% puis on intègre (pour chaque tranche) les 2 signeaux obtenus
% on compare soustrait l'un par l'autre, si c'est négatif on code 1, sinon
% on code 2

% question 4.1.2

signal_cos_F0 = transpose(x_bruit_1) .* cos(2*pi*F0*[0:Te:Te*(length(x_bruit_1)-1)]);
signal_cos_F1 = transpose(x_bruit_1) .* cos(2*pi*F1*[0:Te:Te*(length(x_bruit_1)-1)]);
tranches_signal_FSK_F0 = reshape(transpose(signal_cos_F0), Ns, length(message));
tranches_signal_FSK_F1 = reshape(transpose(signal_cos_F1), Ns, length(message));


tranches_signal_FSK_F0_integre = mean(tranches_signal_FSK_F0);
tranches_signal_FSK_F1_integre = mean(tranches_signal_FSK_F1);

message_restitue_FSK = double((- tranches_signal_FSK_F0_integre + tranches_signal_FSK_F1_integre) > 0)


reconstitution_image(message_restitue_FSK)             
which reconstitution_image 
% partie 2 fsk 



signal_cos_F0_1 = transpose(x_bruit) .* cos(2*pi*F0*[0:Te:Te*(length(x_bruit)-1)]);
signal_cos_F1_1 = transpose(x_bruit) .* cos(2*pi*F1*[0:Te:Te*(length(x_bruit)-1)]);
tranches_signal_FSK_F0_1 = reshape(transpose(signal_cos_F0_1), Ns, length(message));
tranches_signal_FSK_F1_1 = reshape(transpose(signal_cos_F1_1), Ns, length(message));

tranches_signal_FSK_F0_integre_1 = mean(tranches_signal_FSK_F0_1).^2;
tranches_signal_FSK_F1_integre_1 = mean(tranches_signal_FSK_F1_1).^2;



signal_cos_F0_2 = transpose(x_bruit) .* sin(2*pi*F0*[0:Te:Te*(length(x_bruit)-1)]);
signal_cos_F1_2 = transpose(x_bruit) .* sin(2*pi*F1*[0:Te:Te*(length(x_bruit)-1)]);
tranches_signal_FSK_F0_2 = reshape(transpose(signal_cos_F0_2), Ns, length(message));
tranches_signal_FSK_F1_2 = reshape(transpose(signal_cos_F1_2), Ns, length(message));

tranches_signal_FSK_F0_integre_2 = mean(tranches_signal_FSK_F0_2).^2;
tranches_signal_FSK_F1_integre_2 = mean(tranches_signal_FSK_F1_2).^2;

var=tranches_signal_FSK_F1_integre_1 + tranches_signal_FSK_F1_integre_2 - (tranches_signal_FSK_F0_integre_2 + tranches_signal_FSK_F0_integre_1)

message_restitue_FSK = double(var > 0);

reconstitution_image(message_restitue_FSK)             
which reconstitution_image 



