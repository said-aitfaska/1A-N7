function [x_min,x_max,probabilite] = calcul_proba(E_nouveau_repere,p)
    x_min=min(E_nouveau_repere(:,1));
    y_min=min(E_nouveau_repere(:,2));
    x_max=max(E_nouveau_repere(:,1));
    y_max=max(E_nouveau_repere(:,2));
    N=floor((x_max-x_min)*(y_max-y_min));
    probabilite=1-binocdf(length(E_nouveau_repere(:,1)),N,p);     %on utilise la fonction donnee en enonce 
end