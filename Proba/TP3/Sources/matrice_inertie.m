function [C_x,C_y,M] = matrice_inertie(E_x,E_y,G_norme_E)
    pi_fct=sum(G_norme_E);       %G_norme_E represente notre p indice i  de l'enonce
    C_x=1/pi_fct .* sum(G_norme_E.* E_x);
    C_y=1/pi_fct .* sum(G_norme_E.*E_y);
    M(1,1)=1/pi_fct .* sum(G_norme_E.*(E_x-C_x).^2);             %M est de dimension 2 donc on traite chacun des cas 
    M(1,2)=1/pi_fct .*sum(G_norme_E .*(E_x-C_x).*(E_y-C_y));
    M(2,1)=1/pi_fct .* sum(G_norme_E .*(E_y-C_y).*(E_x-C_x));
    M(2,2)=1/pi_fct .* sum(G_norme_E .*(E_y-C_y).^2);
end
    
    