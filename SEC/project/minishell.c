/*
* ******************   PROJET MINISHELL   ***************************
*
* @author : Saïd AIT FASKA 
* @Groupe :  A
*
* *******************************************************************
*/

#include <stdio.h>    /* entrées sorties */
#include <unistd.h>   /* pimitives de base : fork, ...*/
#include <stdlib.h>   /* exit */
#include <signal.h> 
#include <sys/wait.h> /* wait */
#include <string.h>
#include "readcmd.h"
#include "readcmd.c"
#include <errno.h>
#include <fcntl.h>
#define GREEN_COL "\x1b[32m"  /* ANSI vert */
#define END_COL "\x1b[0m"   /* réinitialiser */



enum etat{
	suspendu,Actif,Termine,BG,FG
};
typedef enum etat etat_t;

typedef struct{
	pid_t job_pid;
	int job_ident;
	char* commande_next;
	etat_t job_etat;
}job_t;

typedef struct{
	int Nombre_fils;
	int max_ident; 
	job_t list[];
}liste_jobs;

/*Initialisation de list_jobs */
liste_jobs list_jobs ;

/*front ground executon*/
pid_t pid_foreground;

job_t *job_pid_t;
/*get indice pid */
int get_indice(pid_t pid){
	for(int i=0 ; i<= list_jobs.Nombre_fils ; i++){
		if(list_jobs.list[i].job_pid == pid){
			return i;
		}
	}
	return 0;      // pid pas disponible -> erreur
}

/*get pid from jobs_list */
char get_pid(int identifiant){
	for(int i=0 ; i< list_jobs.Nombre_fils ; i++){
		if(list_jobs.list[i].job_ident == identifiant){
			return list_jobs.list[i].job_pid;
		}
	
	}
	return 0;
}

/* Etat du pid dans la liste */
char Etat_pid(pid_t pid){
	for(int i=0 ; i<= list_jobs.Nombre_fils ; i++){
		if(list_jobs.list[i].job_pid == pid){
			
			return list_jobs.list[i].job_etat;
		}
		//job_curseur = job_curseur[i].suivant;
	}
	return 0;
}

/* ajouyer un pid dans la liste */
void ajouter_pid(int pid, char* cmd, etat_t etat) {
    char *ligne_cmd = malloc(10*sizeof(char));
    list_jobs.list[list_jobs.Nombre_fils].job_ident = list_jobs.max_ident +1;  // augmenter la list d'un pid 
    list_jobs.list[list_jobs.Nombre_fils].job_pid = pid;
    strcpy(ligne_cmd, cmd);
    list_jobs.list[list_jobs.Nombre_fils].commande_next = ligne_cmd;
    list_jobs.list[list_jobs.Nombre_fils].job_etat = etat;
    list_jobs.Nombre_fils++ ;
    list_jobs.max_ident ++;
}
/* afficher le pid , son etat */
void afficher_pid(job_t job_1){
     printf("\n");
     printf(" ----------------------------------------------------- \n");
    if (job_1.job_etat == FG){
        printf(" -- %d   de  pid: %d    est FG    \n" ,job_1.job_ident ,job_1.job_pid); 
	fflush(stdout);
    } else if (job_1.job_etat == BG){
        printf(" -- %d   de  pid: %d   est BG      \n" ,job_1.job_ident ,job_1.job_pid);
        fflush(stdout);
    } else {
        printf(" -- %d   de  pid: %d   est  Suspendu     \n" ,job_1.job_ident ,job_1.job_pid);
        fflush(stdout);
    }
	printf(" ----------------------------------------------------- \n");
}

/* Afficher la liste de jobs */
void afficher_jobs(){  
	//printf("JE SUIS DANS AFFICHER JOBS \n");               
	for(int i=0 ; i<= list_jobs.Nombre_fils ; i++){    /* all list jobs */
		afficher_pid(list_jobs.list[i]);
	}
}
		
/* Delet terminated pid */
void supprimer_pid(pid_t pid){
		for(int i=0 ; i<= list_jobs.Nombre_fils ; i++){	
			list_jobs.list[get_indice(i)].job_etat = suspendu;
			//liste_jobs.Nombre_fils = liste_jobs.Nombre_fils - 1 ;
		}
		list_jobs.Nombre_fils = list_jobs.Nombre_fils - 1 ;
}		
					
void changer_rep(char** cmd) {
    if ( cmd[1] == 0 || !strcmp(cmd[1], "~")) {

        chdir(getenv("HOME"));

    } else if (chdir(cmd[1])==-1) {

        printf(" %s : Aucun fichier ou dossier de ce type.\n", cmd[1]);
    }
}	

void fg_pid(char** cmd){
	   if (cmd[1] == 0) {

        	printf("il faut identifiant du pid execute en FG .\n");

    	   } else if (get_pid(cmd[1]) < 0){

        	printf("id error\n");

   	  } else {
        	int pid = get_pid(atoi(cmd[1]));      
        	int indice = get_indice(pid);          
        	list_jobs.list[indice].job_etat = FG;
        	printf("Pid en FG\n");
        	kill(pid, SIGCONT);
		list_jobs.list[get_indice(pid)].job_etat = FG;
        	//wait(&curseur);		
        	//supprimer_pid(pid);
   	}
  }


void bg_pid(char**cmd){
	 if (cmd[1] == 0) {

        	printf("il faut identifiant du pid execute en BG .\n");
   
    	   } else if (get_pid((cmd[1])) < 0){

        	printf("id error \n");

   	  } else {
		//printf("je suis 2 ");
   	  	int pid = get_pid(atoi(cmd[1]));
   	  	kill(pid,SIGCONT);
   	  	list_jobs.list[get_indice(pid)].job_etat = BG;
	  }
}

/* Stop */
void stop(char ** cmd){
	if (cmd[1] == 0 ) {
		printf("Stoped succesfuly 1/1 \n");
	}else{
			// la fct atoi permet de convertir chaine en entier codage ASCII //
	     int pid = get_indice(atoi(cmd[1]));
	     kill(pid,SIGCONT);
             list_jobs.list[get_indice(pid)].job_etat = suspendu;
	     printf(" process stoped  2/2 \n");
	}
}
	
	
	

/* handler_chld */
void handler_chld(int signal_num) {
     int fils_termine, wstatus ;
     //printf("\nJ'ai reçu le signal %d\n",  signal_num) ;
     do{
	fils_termine = (int) waitpid(-1, &wstatus, WNOHANG | WUNTRACED | WCONTINUED);
     	if ((fils_termine == -1) && (errno != ECHILD)){
              perror("\n waitpid" );
         }
           else if (fils_termine > 0) {
		int k = get_indice(fils_termine);
		if(k != -1){
			if WIFEXITED(wstatus){
				supprimer_pid(fils_termine);
			}
		}
	}
	}while(fils_termine > 0);
		return;    
  }

/*  handler stop */
void handler_SIGTSTP(int sig){
        kill(pid_foreground,SIGSTOP);
        supprimer_pid(pid_foreground);
}

/*Traitant handler Sigint */     //on tue le pid qui est en avant plan //
void handler_SIGINT(int sig) {
        kill(SIGKILL,pid_foreground);
        supprimer_pid(pid_foreground);
}



int main() {
	int retour1,retour3,retour4,retour5,retour6;
	int wstatus1;
	int p1[2],p2[2];
	int fils;
	/* Signaux */

	signal(SIGCHLD, handler_chld);
        signal(SIGSTOP,&handler_SIGTSTP);
	signal(SIGINT,&handler_SIGINT);

	/* signal mask */
	sigprocmask(SIG_BLOCK, &handler_SIGTSTP, NULL);
	sigprocmask(SIG_BLOCK, &handler_SIGINT, NULL);
	while(1){
		printf(GREEN_COL);
		printf("Saïd-ait@faska $ >>> ");
		printf(END_COL);
		fflush(stdout);
		
		//signal(SIGCHLD, handler_chld);
        	//signal(SIGSTOP,&handler_SIGTSTP);
		//signal(SIGINT,&handler_SIGINT);

		struct cmdline *cmd;
		cmd = readcmd(); /* cmd command reader */
		if (cmd->seq[0] != NULL) {   /* no error in cmd */
			/* traiter le cas d'un retour a la ligne */
			if (cmd->seq[0] == NULL) {
           		} 
			// normal exit case //
			if (!strcmp(cmd->seq[0][0], "exit" )) {
				printf("sortiee du pid \n");
				exit(0);
			}
			// list command case //
			else if (!strcmp(cmd->seq[0][0], "jobs" )) {
				
				afficher_jobs();
			}

			else if (!strcmp(cmd->seq[0][0],"cd")){
				
				if ( cmd->seq[1] == 0 || !strcmp(cmd->seq[1], "~")) {

        				chdir(getenv("HOME"));

    				} else if (chdir(cmd->seq[1])<0) {

        				printf("- : Repertoire introuvée.\n");
    				}
				
			}else if (!strcmp(cmd->seq[0][0],"stop" )){
				stop(cmd->seq[0]);
				// foreground execution //
			}else if(!strcmp(cmd->seq[0][0],"FG" )){
		
				fg_pid(cmd->seq[0]);
			}else if(!strcmp(cmd->seq[0][0],"BG")){
				bg_pid(cmd->seq[0]);
			}else{	
				 fils = fork();        /* cretion du Processus fils principale  */
				  
				  if(fils == -1 ){
					perror("Erreur fork \n");
					exit(1);
				  }
				  if (fils == 0) {    /* FILS PRINCIPALE */
 						/* REDIRECTIONS Q8 */
					if (cmd->in != NULL) {
                        			//printf("hello 1\n");
						/*REDIRECTIONS ENTREE STANDART*/
							int dup_des;
							retour1 = open(cmd->in,O_RDONLY);
							if(retour1 < 0){
								perror("Erreur open fichier  \n");
								exit(2);
							}
							dup_des = dup2(retour1,0);
							if(dup_des < 0){
								perror("Erreur dup2\n");
							exit(2);
							}
						int retour2 = execvp(cmd->seq[0][0],cmd->seq[0]);
				        	if(retour2 == -1 ){
				        	/* erreur si n'est pas execute */
				        		perror("erreur exec ICI \n");
				        		exit(2);
				        	}
                   			 }
                    
                    			if (cmd->out != NULL) {
						// printf("hello 2 \n");
						 /*REDIRECTIONS SORTIE STANDART*/
                        			 int fich_out, dup_des2;
    							    fich_out = open (cmd->out, O_WRONLY| O_CREAT | O_TRUNC, 0640);

    							    if (fich_out < 0) {
        								perror("erreur fichier");
        								exit(1);
   							    }

    							    dup_des2 = dup2(fich_out, 1);

    							    if (dup_des2 == -1) {
       								 printf("Erreur dup2 \n");
        							 exit(1);
    							   }
						retour3 = execvp(cmd->seq[0][0],cmd->seq[0]);
				        	if(retour3 == -1 ){
				        	/* erreur si n'est pas execute */
				        		perror("erreur exec ICI \n");
				        		exit(2);
				        	}
                    			}
                    
                   			 /* TUBES SIMPLES  Q9  */
                    			if (!(cmd->seq[1] == NULL)){
                        			/* pipe creation */
                        			if (pipe(p1) == -1){
							/* pipe error */
                            				perror("Error pipe\n");     
                            				exit(1);
                        			}
                       				retour4 = fork();
                        			
                        			if (retour4 < 0) {   
							/* fork error */
                            				printf("Erreur fork\n") ;
                            				exit(1);
                        			}
                        			/* SUB-SON 1 */
                        			else if (retour4 == 0) {
                            				close(p1[0]);
                            				if (dup2(p1[1],STDOUT_FILENO) == -1) { 
								/* dup2 ERROR */  
                                				printf("Erreur dup2\n") ;
                                				exit(EXIT_FAILURE) ;
                           				 }
                            				if (execvp(cmd->seq[0][0], cmd->seq[0]) < 0) {
								/* exec error */
                                				printf("Error\n");
                                				exit(EXIT_FAILURE);
                            				}
                            			/* FATHER son 1 ( retour 1) */
                       				 } else {
                            				close(p1[1]);
                            				if (dup2(p1[0],STDIN_FILENO) == -1) {
								   /* dup2 error*/
                                				printf("Erreur dup2\n") ;
                                				exit(EXIT_FAILURE) ;
                            				}
                            
                            				if (execvp(cmd->seq[1][0], cmd->seq[1]) < 0) {
								/* exec error*/
                                				printf("Error\n");
                               					 exit(EXIT_FAILURE);
                           				 }
                       				 }
                    			} else {  /* ELSE of confition : (!(cmd->seq[1] == NULL)) -> exec for the redirections commands  */
                        			if (execvp(cmd->seq[0][0], cmd->seq[0]) < 0){
							/* exec error */
                            				printf("Error\n");
                            				exit(EXIT_FAILURE);
                        			}
                   			 }
                    
                    			/* Pipelines Q10 */
                    			if (pipe(p1) == -1){
						/* pipe error */
                        			perror("Error pipe\n");     
                       				 exit(1);
                    			}
                    
                    			retour5 = fork();
                    			if (retour5 < 0) {   
						/*fork error */
                        			printf("Erreur fork\n") ;
                       				 exit(1);
                    			}
                   			 /* SUB-SON  2 */
                   			 else if (retour5 == 0) {
                        			close(p1[0]);
                        			if (dup2(p1[1],STDOUT_FILENO) == -1) {  
							 /*dup2 error  */
                            				printf("Erreur dup2\n") ;
                            				exit(EXIT_FAILURE) ;
                        			}
                        			close(p1[1]);
                        
                        			if (pipe(p2) == -1){
							/*pipe error */
                           				 perror("Error pipe\n");     
                            				exit(1);
                        			}
                        			retour6 = fork();
                       				 if (retour6 < 0) {   
							/*fork error */
                           				 printf("Erreur fork\n") ;
                            				 exit(1);
                        			}
                        			/* SUB-SON 2  */
                        			else if (retour6 == 0) {
                            				close(p2[0]);
                            				if (dup2(p2[1],STDOUT_FILENO) == -1) {  
								 /*dup2 error */
                                				 perror("Erreur dup2\n") ;
                               					 exit(EXIT_FAILURE) ;
                           				 }
                           				 close(p2[1]);
                            				if (execvp(cmd->seq[0][0], cmd->seq[0]) < 0){
								 /*exec error */
                               				 	perror("Error exec\n");
                                				exit(EXIT_FAILURE);
                            				}
                       				 } else {  /*FATHER SUB-SON 2*/
                           				 close(p2[1]);
                            				if (dup2(p2[0],STDIN_FILENO) == -1) {   
								/*dup2 error  */
                                				perror("Erreur dup2\n") ;
                                				exit(EXIT_FAILURE) ;
                            				}
                            				close(p2[0]);
                            
                            				if (execlp(cmd->seq[1][0],cmd->seq[1][0],cmd->seq[1][1],NULL) < 0){
								/* exec error */
                                				perror("Error\n");
                                				exit(EXIT_FAILURE);
                            				}
                        			}
                    			}
			        		
				}else{  /* PERE  principale*/
										  
					ajouter_pid(fils,cmd->seq[0],Actif);		
					
					/*   backgrouded( readcmd.h)  verifier si BF OU FG et ajouter a la liste avec ETAT   */
					if(cmd->backgrounded == NULL){
						ajouter_pid(fils,*cmd->seq[0],FG);
						pid_foreground = fils;
						wait(&wstatus1); 
						supprimer_pid(fils);
 					}else{
						ajouter_pid(fils,*cmd->seq[0],BG);
						
					}
				}
			}		
		}
 	}
	exit(EXIT_SUCCESS);	
}	
 
