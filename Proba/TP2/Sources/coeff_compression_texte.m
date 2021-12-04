function coeff_compression = coeff_compression_texte(texte,texte_encode)
    coeff_compression = (8*length(texte))/length(texte_encode);  % Le caractere est code sur 8 bits + definition de coeff_compression
end 