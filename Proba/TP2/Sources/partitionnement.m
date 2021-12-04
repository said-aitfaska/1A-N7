function bornes = partitionnement(selection_frequences)
    bornes(2,1)=selection_frequences(1);
    bornes(1,1)=0;                   %La matrices bornes on a 0 dans la case (1,1)
    for i=2:length(selection_frequences)    %a partir de la deuxieme ligne qu'on commenece a changer la frequences des caracteres 
        bornes(1,i)=bornes(2,i-1);       
        bornes(2,i)=selection_frequences(i)+bornes(2,i-1);
    end
end

