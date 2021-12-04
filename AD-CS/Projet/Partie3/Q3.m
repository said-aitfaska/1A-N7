clear;
close all;

load eigenfaces;
pourcentage =zeros(7,1);
ListeClasse = 1:32;
for N=8:14
    
for i=1:32
    for j=1:6
    
% si on veut tester/mettre au point, on fixe l'individu
personne = i
posture = j

ficF = strcat('./Data/', liste_personnes{personne}, liste_postures{posture}, '-300x400.gif')
img = imread(ficF);
image_test = double(transpose(img(:)));
 

% Pourcentage d'information 
per = 0.95;

% Nombre N de composantes principales a prendre en compte 
% [dans un second temps, N peut etre calcule pour atteindre le pourcentage
% d'information avec N valeurs propres (contraste)] :


%Composantes principales des données d'apprentissages 
C_1 = X_centre * W;

%N composantes de image test 
C_t = (image_test - individu_moyen)*W;
N_Test =C_t (:,1:N);
%N composantes principales des images d'apprentissages 
N_Appr = C_1(:,1:N);

labelA = repmat(1:nb_personnes,nb_postures,1);

%On cherche l'individu le plus proche 
k = 1; 

%Algorithme de plus proche voisin :    
   %Calcul distance 
   [Na,~] = size(N_Appr);
   distances_images = vecnorm((( ones (Na , 1) * N_Test(1 , :) )- N_Appr )')';
   % on garde les k proches 
  K = 1;     % car on a calculé les composantes principales 
   [~,indices] = sort(distances_images);
  voisins = indices(1:K);
   
   p = ones(1,nb_postures);
   
   labelA = repmat(1:nb_personnes,nb_postures,1); 
   labels= labelA(voisins);
   compt = hist(labels,ListeClasse);  
   
   % Recherche de la classe contenant le maximum de voisins
   % À COMPLÉTER
   [valeurMax,indiceClasse] = sort(compt,'descend');
    
   proche = distances_images(voisins(1));
   if length(find(compt == valeurMax))> 1
        Partition = labelA(voisins(1));
    else
        Partition = labelA(indiceClasse);
   end
if (mod(Partition,4) == 0)
       posture_proche = 4;
       personne_proche = fix(Partition/4);
else
    posture_proche = mod(Partition,4);
    personne_proche = fix(Partition/4)+1;
end  
% pour l'affichage (A CHANGER)
%personne_proche = 1;
%posture_proche = 1;
% Affichage de l'image de test :
  if (posture==posture_proche) 
      pourcentage(N-7) = pourcentage(N-7) +1;
  end 
  if (personne == personne_proche) 
      pourcentage(N-7) = pourcentage(N-7) +1; 
  end 
   end
end
  
end
for i=8:14
    disp('Le nombre de postures et de personnes reconnues pour N =');
    disp(i);
    disp('est');
