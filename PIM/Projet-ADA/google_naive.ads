--********************************************************************
   ------ PROJET   :  Pagerank 2020-2021                             
   ------ FICHIER  :  Google_naive.ads                               
   ------ AUTEUR   :  AIT FASKA Saïd                                 
--********************************************************************


with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO;    use Ada.Float_Text_IO;

--Specification du module Google_naive---

generic
   Capacite : Integer;
   --type T_Vecteur is private;
  -- PRECISION : Integer;
   
package Google_naive is
   
   
   type T_Tableau is   private;
   type T_Vecteur is private;


   ------------- Specification sous-programmes initiales --------------------
   
   -- Nom : Initialiser.
   -- Sémantique : Initialiser Tableau  . Tableau est vide.
   -- Paramétres :
           -- tableau ;         -- tableau qu'on va initialiser.
   procedure Initialiser(Tab : out T_Tableau ) with
     Post => Est_Nul(Tab);
   
   
   -- Nom : Est_Nul.
   -- Sémantique : Le tableau est bien vide?.
   -- Parametres : 
          -- tableau.
   function Est_Nul(Tab:in T_Tableau) return Boolean ;
   
   
  
   -- Nom : Est_ligne_Nul .
   -- Sémantique : Verifier que le tableau est Null;
   -- Parametres :
           -- tableau ;       -- tableau qu'on va verifier qu'est bien null.
   function Est_ligne_Nul(Tab: in T_Tableau ; indice : in Integer) return Boolean ;
   
    
   ---------- Specifiaction des sous-programmes principales ---------
   
   -- Nom : Sommer_ligne .
   -- Sémantique : Sommer les elements d'une ligne .
   -- Parametres : 
         -- tableau;          -- On veut sommer les elemenst de ses lignes.         
   function Sommer_ligne(Tab: in T_Tableau;Indice : in Integer) return float with      -- PAS UTILE !!
     post => Sommer_ligne'Result >= 0.0;
   
   
   -- Nom : Remplir_ligne
   -- Sémantique : Remplir la ligne du tableau avec 1/Capacite;.
   -- Parametres : 
          -- Tableau.
   procedure Remplir_ligne(Tab: in out T_Tableau;indice:in Integer);  -- car on remplir avec 1/N
     --Post => not(Est_ligne_Nul(Tab , indice ));
   
   procedure Multiplier_ligne(Tab:in out T_Tableau;indice:in Integer;Element:in Float);  
   
   procedure Enregistrer_tab(Tab:in out T_Tableau;Ligne:in Integer;Colonne:In integer;Element:in Float);
   
   procedure Multiplier_tab(Tab: in out T_Tableau;Scalaire:in Float);
   
   procedure Remplir_tab(Tab: in out T_Tableau;Scalaire: in Float);
   
   procedure Initialiser_vecteur(Vect:out T_Vecteur;Element: in Float);
   
   
   -- Nom : Transposer.
   -- Sémantique : Transposer un vecteur.
   -- Parametres : 
       -- vecteur e .
   --procedure Transposer(Tab: in out T_Tableau) ;
               
    
   -- Nom : Somme_tab.
   -- Sémantique : Sommer deux tableaux.
   -- Parametres : 
            -- Tab1 , Tab2.
   procedure Somme_tab(Tab1:in out T_Tableau ; Tab2:in T_Tableau) ;
   
   
   -- Nom : Produit_tab.
   -- Semantique : Multiplier deux tableaux.
   -- Parametres : 
            -- Tab1,Tab2.
   procedure Produit_tab(Vect:in out T_Vecteur ; Tab:in T_Tableau);
   
                     
   -- Nom : Trier_decroissant .
   -- Sémantique : Trier le vecteur indice dans l'ordre decroissant .
   -- Paramétres : 
          -- vect1,vect2.
   procedure Trier_decroissant (Vect1:in out T_Vecteur;Vect2:in out T_Vecteur) ; 
     --Pre => not
    
   
   -- Nom : Afficher.
   -- Sémantique : Afficher le tableau.
   -- Parametres : 
         -- tableau.
   procedure Afficher(Tab: in T_Tableau) ;
   
   -- Nom : Afficher_Vecteur
   -- Sémantique : Afficher un vecteur.
   -- Parametres : 
           -- Vecteur .
   procedure Afficher_Vecteur(Vect: in T_Vecteur);
   
   
   procedure generer_tableau(Tab: in out T_Tableau) ; 
   
   procedure generer_vecteur(Vect: out T_Vecteur);
   procedure generer_vecteur2(Vect:out T_Vecteur);
   
   
   
private
   -- ajjouter le type double of digits....
   -- Type T_Double is digits ...
   type T_Tableau is array(1..Capacite,1..Capacite) of Float;
   type T_Vecteur is array(1..Capacite) of Float;
   
end Google_naive;
