function frequences= calcul_frequences(texte,alphabet)
    frequences=(length(alphabet));
    for i = 1 : length(alphabet)         % on parcourt toute la longeur de alphabet
        indices=find(texte == alphabet(i));  
        frequences(i) = length(indices)/length(texte);   %frequence relative des caracteres 
    end
end

    