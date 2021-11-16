
--********************************************************************
   ------ PROJET   :  Pagerank 2020-2021                             
   ------ FICHIER  :  Google_naive.adb                               
   ------ AUTEUR   :  AIT FASKA Saïd      
--********************************************************************


with Ada.Text_IO;            use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Ada.Integer_Text_IO;  use Ada.Integer_Text_IO;
with Ada.Float_Text_IO;    use Ada.Float_Text_IO;

package body google_naive is 
   
   
   procedure generer_tableau(Tab :in out T_Tableau) is
   begin 
      Tab(1,1) := 1.0;
      Tab(1,2) := 2.5;
      Tab(1,3) := 0.0;
      Tab(2,1) := 0.0;
      Tab(2,2) := 0.0;
      Tab(2,3) := 0.2;
      Tab(3,1) := 1.1;
      Tab(3,2) := 0.8;
      Tab(3,3) := 9.9;
   end generer_tableau;
        
   procedure generer_vecteur(Vect:out T_Vecteur) is 
   begin
      Vect(1) := 1.4;
      Vect(2) := 8.0;
      Vect(3) := 3.2;
   
   end generer_vecteur; 
   
   procedure generer_vecteur2(Vect:out T_Vecteur) is
   begin
      Vect(1) := 0.5;
      Vect(2) := 6.7;
      Vect(3) := 3.2;
   end generer_vecteur2;   
      
   procedure Initialiser(Tab: out T_Tableau) is
   begin
      for I in 1..Capacite loop
         for K in 1..Capacite loop
            Tab(I,K):=0.0;
         end loop;
      end loop;
   end Initialiser;
   
   procedure Initialiser_vecteur(Vect:out T_Vecteur;Element: in Float) is
   begin
      for I in 1..Capacite loop
         Vect(I):=Element;
      end loop;
   end Initialiser_vecteur;
      
   
   function Est_nul(Tab: in T_Tableau) return Boolean is
      val:Boolean;
   begin
      val:=True;
      For I in 1..Capacite loop
         for K in 1..Capacite loop
            if Tab(I,K) /= 0.0 then
               val:= False;
            end if;
         end loop;
      end loop;
      return val;
   end Est_nul;
   
   
   
   function Est_ligne_Nul(Tab:in T_Tableau;indice : in Integer) return Boolean is
      ligne_Vide:Boolean;
   begin
      ligne_Vide:=True;
      for I in 1..Capacite loop
            if Tab(indice,I) /= 0.0 then
               ligne_Vide:=False;
            end if;
      end loop;
      return ligne_Vide;
   end Est_ligne_Nul;
      
   
   function Sommer_ligne(Tab: in T_Tableau; indice: in Integer) return Float is
      somme:Float:=0.0;
   begin
      for I in 1..Capacite loop
         somme := somme + Tab(indice,I);         -- Sommer les elements d'une ligne 
      end loop; 
      return somme;
   end Sommer_ligne;
   
   procedure Remplir_ligne(Tab:in out T_Tableau;indice:in Integer) is         -- Remplir la matrice avec 1/Capacite
   begin
      for I in 1..Capacite loop                                                     
            Tab(indice,I):=1.0/Float(Capacite);
      end loop;
   end Remplir_ligne;
   
   --ajjouter remplir_vecteur;
   
   procedure Multiplier_ligne(Tab:in out T_Tableau;indice:in Integer;Element:in Float) is   
   begin
      for I in 1..Capacite loop                                                     
            Tab(indice,I):= Tab(indice,I)*Element;
      end loop;
   end Multiplier_ligne;
   
   
   
 --  procedure Transposer(Tab : in out T_Tableau) is
 --     transp:T_Tableau;
 --  begin
 --     for K in 1..Capacite loop   
 --        for J in 1..Capacite loop
 --           transp(K,J):=Tab(J,K);          
  --       end loop;     
  --    end loop;
  --    Tab:=transp;
 --  end Transposer;
   
   procedure Somme_tab(Tab1:in out T_Tableau;Tab2: in T_Tableau) is 
   begin
      for K in 1..Capacite loop 
         for J in 1..Capacite loop
            Tab1(K,J):=Tab1(K,J) + Tab2(K,J);
         end loop;
      end loop;
   end Somme_tab;
  
   procedure Produit_tab(Vect:in out T_Vecteur;Tab:in T_Tableau)  is    -- faire une raise error si pas meem dimensions 
      Produit:T_Vecteur;
   begin
      for K in 1..Capacite loop
         Produit(K) := 0.0;
         for J in 1..Capacite loop         
            Produit(K) := Produit(K) + Vect(J)*Tab(J,K);           
         end loop;
      end loop;
      Vect := Produit;
      --return Produit;
   end Produit_tab;
   
   procedure Multiplier_tab(Tab: in out T_Tableau;Scalaire:in Float) is
   begin
      for I in 1..Capacite loop
         Multiplier_ligne(Tab,I,Scalaire);
      end loop;
   end Multiplier_tab;
   
   procedure Remplir_tab(Tab: in out T_Tableau;Scalaire: in Float) is
   begin
      for I in 1..Capacite loop
         for K in 1..Capacite loop
            Tab(I,K):=Scalaire;
         end loop;
      end loop;
   end Remplir_tab;
   
   procedure Enregistrer_tab(Tab:in out T_Tableau;Ligne:in Integer;Colonne:In integer;Element:in Float) is
   begin
      Tab(Ligne,Colonne):=Element;
   end Enregistrer_tab;
         
               
   procedure Trier_decroissant(Vect1:in out T_Vecteur;Vect2:in out T_Vecteur) is
      position: Integer;
      Memoire_Poids : Float;
      Memoire_Indice : Float;
   
   begin
      for I in 1..Capacite loop
         Memoire_Poids := Vect1(I);
         Memoire_Indice := Vect2(I);
         position := I;
         while position > 1 and then Vect1(position-1) < Memoire_Poids loop
            position:=position-1;
         end loop;
         for k in position+1..I loop
            Vect1(k):=Vect1(k-1);
            Vect2(k) := Vect2(k-1);
         end loop;
         Vect1(position):=Memoire_Poids;
         Vect2(position) := Memoire_Indice;
      end loop;
   end Trier_decroissant;
      
       
   procedure Afficher(Tab:in T_Tableau) is
   begin
      Put ('[');
         for I in 1..Capacite loop 
            Put (Tab(I,1), 1);
            for K in 2..Capacite loop
               Put (", ");
               Put (Tab(I,K), 1); 
               if K = Capacite and then I = Capacite then  
                  Put(']');
               end if;
            end loop;
         New_Line; 
         end loop;      
   end Afficher;  
   
   procedure Afficher_Vecteur (Vect: in T_Vecteur) is
   begin
      Put ('[');
      Put (Vect(1));
      for K in 2..Capacite loop
         Put( " , " );
         Put( Vect(K));
      end loop;
      Put(']');
   end Afficher_Vecteur;
      
         
   
     
end google_naive;
