Require Import Naturelle.
Section Session1_2020_Logique_Exercice_1.

Variable A B C : Prop.

Theorem Exercice_1_Naturelle :  ((A -> C) \/ (B -> C)) -> ((A /\ B) -> C).
Proof.
I_imp H0.
I_imp H1.
destruct H0.
E_imp A.
Hyp H.
destruct H1.
Hyp H0.
E_imp B.
Hyp H.
E_et_d A.
Hyp H1.
Qed.


Theorem Exercice_1_Coq : ((A -> C) \/ (B -> C)) -> ((A /\ B) -> C).
Proof.
intros.
cut (B -> C).
intros.
cut B.
exact H1.
elim H0.
intros.
exact H3.
intros.
destruct H.
cut A.
exact H.
E_et_g B.
exact H0.
cut B.
exact H.
exact H1.
Qed.

End Session1_2020_Logique_Exercice_1.

