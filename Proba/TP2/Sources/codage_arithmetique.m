function [borne_inf,borne_sup] = codage_arithmetique(texte,selection_alphabet,bornes)
    borne_inf= 0;
    borne_sup = 1;
    a= length(texte);
    for i=1:a
       c = find(selection_alphabet==texte(i));
            largeur= borne_sup - borne_inf ;
            borne_sup = borne_inf + largeur * bornes(2,c);
            borne_inf= borne_inf + largeur*bornes(1,c);
        
    end
end
        
        
        
        
        
        
        
        
        
    
        