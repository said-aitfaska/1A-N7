clear;
close all;

load eigenfaces;

% Tirage aleatoire d'une image de test :
personne = randi(nb_personnes)
posture = randi(nb_postures)
% si on veut tester/mettre au point, on fixe l'individu
personne  = 1
posture = 5

ficF = strcat('./Data/', liste_personnes{personne}, liste_postures{posture}, '-300x400.gif');
img = imread(ficF);
img(ligne_min:ligne_max,colonne_min:colonne_max) = 0;
image_test = double(transpose(img(:)));

figure('Name','Image tiree aleatoirement','Position',[0.2*L,0.2*H,0.8*L,0.5*H]);

subplot(1, 2, 1);
% Affichage de l'image de test :
colormap gray;
imagesc(img);
title({['Individu de test : posture ' num2str(posture) ' de ', liste_personnes{personne}]}, 'FontSize', 20);
axis image;
 

% Pourcentage d'information 
per = 0.95;

% Nombre N de composantes principales à prendre en compte 
% [dans un second temps, N peut etre calcule pour atteindre le pourcentage
% d'information avec N valeurs propres (contraste)] :
N = 12;

C = X_centre_masque*W_masque;
C = C(:,1:N);

L_perso = 1:nb_personnes_base;
L_perso = repelem(L_perso, nb_postures_base)';
L_postu = 1:nb_postures_base;
L_postu = repmat(L_postu, 1, nb_personnes_base)';

Data_test = (image_test-individu_moyen_masque)*W_masque(:,1:N);

personne_proche = kppv(C, L_perso, Data_test, 1);
posture_proche = kppv(C, L_postu, Data_test, 1);

ficF = strcat('./Data/', liste_personnes_base{personne_proche}, liste_postures{posture_proche}, '-300x400.gif')
img_p = imread(ficF);
for i = ligne_min:ligne_max,
    for j = colonne_min:colonne_max,
        img(i,j)=img_p(i,j);
    end
end    
        
subplot(1, 2, 2);
imagesc(img);
title({['Individu la plus proche : posture ' num2str(posture_proche) ' de ', liste_personnes_base{personne_proche}]}, 'FontSize', 20);
axis image;
