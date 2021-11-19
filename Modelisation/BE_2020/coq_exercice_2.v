Require Import Naturelle.
Section Session1_2020_Logique_Exercice_2.

Variable A B : Prop.

Theorem Exercice_2_Naturelle : (A -> B) -> ((~A) \/ B).
Proof.
I_imp H0.
E_ou A (~A).
TE.
I_imp H1.
I_ou_d.
E_imp A.
Hyp H0.
Hyp H1.
I_imp H3.
I_ou_g.
Hyp H3.
Qed.


Theorem Exercice_2_Coq : (A -> B) -> ((~A) \/ B).
Proof.
intros.
E_ou A (~A).
TE.
intros.
right.
cut A.
exact H.
exact H0.
intros.
left.
exact H0.
Qed. 
(* A COMPLETER *)


End Session1_2020_Logique_Exercice_2.

