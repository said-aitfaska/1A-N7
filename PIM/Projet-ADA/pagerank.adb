
--********************************************************************
------ PROJET   :  Pagerank 2020-2021                             
------ FICHIER  :  Pagerank.adb                               
------ AUTEUR   :  AIT FASKA Saïd                                 
--********************************************************************


with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO;    use Ada.Float_Text_IO;
with Google_naive;   
--with Google_creuse;        ## a la fin du projet ## 
with ada.Command_Line;     use ada.Command_Line;
with Ada.Strings.Unbounded;


procedure  Pagerank is 
   
   Capacite_teste : constant integer :=3;
   
   package google is new Google_naive(Capacite =>Capacite_teste);
   use google;
   
         
   Matrice_Google : T_Tableau;
   Vect_pi : T_Vecteur;
   N:Integer;                    -- Nombre d'iterations 
   alpha :Float;
begin 
   new_line;                                                  -- lecture de paramtres de commande
   Put("---- Arguments de la ligne de commande : ");
   Put_line(" argument(s).");
   for i in 1 .. Argument_Count loop
      Put("Argument");
      Put(i,1);
      Put(" : ");
      Put_line(Argument(i));
   end loop;
   new_line(2);
   if argument_count(1)= "-P" then                 -- On execute l'algorithme google_naive 
      open(File, In_File, Argument(6));            -- il faut mettre le nom du fichier de test 
      -- ajjouter l'exception de constraint error ( voir retour raffinages general )
      Initialiser(Matrice_Google);                             
      for J in 1..Capacite loop
         for K in 1..Capacite loop
            
      
            -- lecture du fichier reseau 
            -- remplir la matrice Matrice de Google a partir de reseau  
            Modifier_ligne(Matrice_Google,J);                                        -- Pour sommer_ligne ou le mettre !?
            Matrice_Google:=Matrice_Google(J,K)*alpha + (1.0-alpha)/Float(N) * Matrice_Google;    -- Calcule matrice G eq (2) ce N est celui dans le fichier test
            for I in 1..N loop  
               Vect_pi := Produit_tab(Vect,Matrice_Google);
            end loop;
         end loop;
         Trier_decroissant(Vect1,Matrice_Google(1,J));
      end loop;   
      --ecrire dans les fichiers pagerank le tri_decroissant?
   
                                             
   end if;     
      
      
      
   
end Pagerank;
