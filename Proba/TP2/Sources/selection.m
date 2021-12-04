function [selection_frequences,selection_alphabet] = selection(frequences,alphabet)
    selection_frequences= frequences(find(frequences > 0));   % On utilise la fonction find donne en enonce pour avoir les freq positives 
    selection_alphabet = alphabet(find(frequences > 0));       % idem 
end 
    