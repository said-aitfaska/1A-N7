clear;
close all;

load eigenfaces;

% Tirage aleatoire d'une image de test :
 personne = randi(nb_personnes)
 posture = randi(nb_postures)
% si on veut tester/mettre au point, on fixe l'individu
personne = 1
posture = 5

ficF = strcat('./Data/', liste_personnes{personne}, liste_postures{posture}, '-300x400.gif')
img = imread(ficF);
image_test = double(transpose(img(:)));
 

% Pourcentage d'information 
per = 0.95;

% Nombre N de composantes principales a prendre en compte 
% [dans un second temps, N peut etre calcule pour atteindre le pourcentage
% d'information avec N valeurs propres (contraste)] :
N = 8;

C = X_centre*W;
C = C(:,1:N);

liste_perso = 1:nb_personnes_base;
liste_perso = repelem(liste_perso, nb_postures_base);
liste_postu = 1:nb_postures_base;
liste_postu = repmat(liste_postu,1, nb_personnes_base);

dt_test = (image_test-individu_moyen)*W(:,1:N);
personne_proche = kppv(C,liste_perso,dt_test, 1);
posture_proche = kppv(C,liste_postu(:), dt_test, 1);

figure('Name','Image tiree aleatoirement','Position',[0.2*L,0.2*H,0.8*L,0.5*H]);

subplot(1, 2, 1);
% Affichage de l'image de test :
colormap gray;
imagesc(img);
title({['Individu de test : posture ' num2str(posture) ' de ', liste_personnes{personne}]}, 'FontSize', 20);
axis image;


ficF = strcat('./Data/', liste_personnes_base{personne_proche}, liste_postures{posture_proche}, '-300x400.gif')
img = imread(ficF);
        
subplot(1, 2, 2);
imagesc(img);
title({['posture :' num2str(posture_proche) ' de ', liste_personnes_base{personne_proche}]}, 'FontSize', 20);
axis image;
