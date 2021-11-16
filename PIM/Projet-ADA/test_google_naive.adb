--********************************************************************
   ------ PROJET   :  Pagerank 2020-2021                             
   ------ FICHIER  :  Test_Google_naive.adb                               
   ------ AUTEUR   :  AIT FASKA Saïd                                
--********************************************************************

with Ada.Text_IO;            use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Ada.Integer_Text_IO;  use Ada.Integer_Text_IO;
with Ada.Float_Text_IO;    use Ada.Float_Text_IO;
with Google_naive;         


procedure Test_google_naive is
   

   Capacite_teste : constant integer :=3;
   
   package google is new Google_naive(Capacite =>Capacite_teste);
   use google;
   
  
   
   procedure Tester_generer is
      Tableau: T_Tableau;
     
   begin
      generer_tableau(Tableau);
      afficher (Tableau);
   end Tester_generer;
      
      
   procedure Tester_initialiser is 
      Tableau: T_Tableau;
    begin
      Put("      === Tester Initialiser le tableau..       " );
      New_Line;
      Initialiser(Tableau);
      pragma assert((Est_Nul(Tableau)));
      Afficher(Tableau);
      Put("     ====  Test Initialiser ;reussi ! ====      ");
      New_line(2);
   end Tester_initialiser;
    
   
   procedure Tester_Est_Nul is  
      Tableau: T_Tableau;
   begin
      Put( "         === Tester Est_Nul ...       ");
      generer_tableau(Tableau);
      --Initialiser(Tableau);
      pragma assert(Est_Nul(Tableau));
      if (Est_Nul(Tableau)) then
         pragma assert(Est_Nul(Tableau) = True);
         New_Line;
         Put( " Le Tableau est Vide   ");
      else
         pragma assert(Est_Nul(Tableau) = False);
         New_Line;
         Put( "   Le Tableau n'est pas Vide    ");
      end if;
      New_Line;
      Put( "       ==== Tester Est_nul reussi !  ==== ");
      new_Line(2);
   end Tester_Est_Nul;
   
   procedure Tester_Est_ligne_nul is
      Tableau: T_Tableau;
   --   Capacite : Integer;
   begin
      Put ( "      === Tester Est_ligne_NUL ...     ");
      New_Line(2);
      generer_tableau(Tableau);
      for I in 1..Capacite_teste loop
         if (Est_ligne_Nul(Tableau,I)) then 
            pragma assert(Sommer_ligne(Tableau,I)=0.0);
            Put ( " C'est bon  cette ligne est  Vide !");
            New_Line;            
         else
            pragma assert(Sommer_ligne(Tableau,I)/=0.0);
            New_Line;
            Put( "  Cette ligne n'est pas Vide ! ");
            New_Line;
        
         end if; 
      end loop;
      New_Line;
      Put( "   ==== Tester Est_ligne_nul reussi ! ==== ");
      New_line(2);
      end Tester_Est_ligne_nul;
       
   procedure Tester_somme_ligne is
      Tableau: T_Tableau; 
    --  Capacite : Integer; 
   begin
      Put( "    ==== Tester somme_ligne ====       ");
      New_line;
      generer_tableau(Tableau);
      --Initialiser(Tableau);
      pragma assert(Est_Nul(Tableau));
      for J in 1..Capacite_teste loop
         if not(Est_Nul(Tableau)) then        
            Put(" La somme de la ligne  est :" );           
            Put(Sommer_ligne(Tableau,J));           
            pragma assert(Sommer_ligne(Tableau,J) /=0.0);            
            New_Line;
         else 
            pragma assert(Sommer_ligne(Tableau,J) = 0.0);
            Put(" La ligne est Nulle !");
            
            new_line;
         end if;
      end loop;
      Put( "     ==== Tester somme_ligne reussi !  ====    ");
      New_line(2);
   end Tester_somme_ligne;
   
   procedure Tester_Modifier_ligne is
      Tableau: T_Tableau;
     -- Capacite : Integer;
   begin
      Put("    ==== Tester Modifier_ligne ====     ");
      New_line;
      generer_tableau(Tableau);
      --Initialiser(Tableau);                -- Pour initialiser le tableau et tester l'autre cas
      afficher(Tableau);
      New_line;
      for K in 2..Capacite_teste loop
         if (Est_ligne_Nul(Tableau,K)) then 
            pragma assert(Sommer_ligne(Tableau,K)>0.0);
            Modifier_ligne(Tableau,K);         
         else
            pragma assert(not(Est_ligne_Nul(Tableau,K)));
            null;
         end if;
      end loop;
      afficher(Tableau);
      New_line;
      Put( "    ==== Tester Modifier_ligne reussi ! ====    ");
      new_line(2);
    end Tester_Modifier_ligne;
   
 
    

   
   procedure Tester_Somme_tab is 
     -- Capacite : Integer;
      Tab1: T_Tableau;
      Tab2: T_Tableau;
      Tab3: T_Tableau;
   begin
      Put( "              ==== Tester Somme_tab =====     ");
      New_Line;
      afficher(Tab1);
      pragma assert(not(Est_Nul(Tab1)));
      New_Line;
      afficher(Tab2);
      pragma assert(not(Est_Nul(Tab2)));
      New_Line;
      Put( " La somme est : ");
      New_line;
      afficher(somme_tab(Tab1,Tab2));
      Tab3 := Somme_tab(Tab1,Tab2);
      pragma assert(not(Est_Nul(Tab1)));
      for I in 1..Capacite_teste loop
         for K in 1..Capacite_teste loop
            pragma assert(Sommer_ligne(Tab3,K) = Sommer_ligne(Tab1,K) + Sommer_ligne(Tab2,K));
         end loop;
      end loop;
      New_line;
      Put( "             ==== Tester Somme_Tab reussi ! ====    ");
      New_line(2);
   end Tester_Somme_tab;    
      
   procedure Tester_Produit_tab is 
      Vect1:T_Vecteur;
      Tableau:T_Tableau;
      Vect2: T_Vecteur;
   begin
      Put( "             ==== Tester Produit_tab =====      ");
      New_Line;
      generer_tableau(Tableau);
      Afficher(Tableau);
      New_line;
      generer_vecteur(Vect2);
      New_line;
      Afficher_Vecteur(Vect2);
      new_line;
      Produit_tab(Vect2,Tableau);
      --pragma assert(Vect1(1)  Vect1(2));
      new_line;
      Afficher_Vecteur(Vect1);
      New_line;
      Put("           ==== Tester Produit_tab reusii ! ====    ");
      New_Line(2);
   end Tester_Produit_tab;
   
   
   procedure Tester_Trier_decroissante is
      Vect1: T_Vecteur;
      Vect2: T_Vecteur;
      
   begin
      Put( "       ==== Tester_trier_decroissant  ====        ");
      New_line;
      generer_vecteur(Vect1);
      generer_vecteur2(Vect2);
      Afficher_Vecteur(Vect1);
      new_line;
      Afficher_Vecteur(Vect2);
      new_line;
      Trier_decroissant(Vect1,Vect2);
     -- pragma assert(Sommer_ligne( > Vect1(2));
      new_line(2);
      Put(" apres tri_decroissante : ");
      new_line;
      Afficher_Vecteur(Vect1);
      New_line;
      Afficher_Vecteur(Vect2);
   end Tester_Trier_decroissante;
   
      

   
begin
   Put ( " ******************** DEBUT TEST *****************");
   New_Line(2);
   Tester_generer;
   Tester_initialiser;
   Tester_Est_Nul;
   Tester_Est_ligne_nul;
   Tester_somme_ligne;
   Tester_Modifier_ligne;
   Tester_Somme_tab;
   Tester_Produit_tab;
   Tester_Trier_decroissante;
   New_line(2);
   Put(" ********************** FIN TEST ********************");
  
   
end Test_google_naive;
