function [E,contour,G_somme] = recursion(E,contour,G_somme,i,j,voisins,G_x,G_y,card_max,cos_alpha)
% Fonction recursive permettant de construire un ensemble candidat E

contour(i,j) = 0;
G_somme_normalise = G_somme/norm(G_somme);
nb_voisins = size(voisins,1);
k = 1;
while k<=nb_voisins & size(E,1)<=card_max
	i_voisin = voisins(k,1) + i	;			
	j_voisin = voisins(k,2) + j ;				
	if contour(i_voisin,j_voisin)
        gradient_voisin = [G_x(i_voisin, j_voisin),G_y(i_voisin,j_voisin)] / norm([G_x(i_voisin, j_voisin), G_y(i_voisin,j_voisin)]);  
        produit_scalaire= dot(G_somme_normalise,gradient_voisin);
        if (produit_scalaire > cos_alpha)
            E =[E; i_voisin j_voisin];  
            G_somme= G_somme +[G_x(i_voisin, j_voisin) G_y(i_voisin, j_voisin)];
            [E,contour,G_somme]=recursion(E,contour,G_somme,i_voisin,j_voisin,voisins,G_x,G_y,card_max,cos_alpha);
        end
    end
	k = k+1;
end
