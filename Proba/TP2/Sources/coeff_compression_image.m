function coeff_compression_avant_decorrelation = coeff_compression_image(histogramme,dico)
    nombre_bits_sans_compression=8 * sum(histogramme);
    nombre_bits_avec_compression=0;
    for i=1:length(histogramme)
        nombre_bits_avec_compression = nombre_bits_avec_compression + histogramme(i)* length(dico{i,2});
    end
    coeff_compression_avant_decorrelation = nombre_bits_sans_compression/nombre_bits_avec_compression;
end