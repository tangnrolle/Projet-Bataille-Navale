# Projet-Bataille-Navale


## Avancement du projet
Je suis allé jusqu'à la fin de l'exercice 8 pour ce projet. J'ai donc une bataille navale fonctionnelle d'un joueur contre l'IA.
À noter que j'ai supprimé après l'exercice 7 tous les affichages que j'avais créé et qui étaient redondants avec ceux de la classe `"Game.java"` de l'exo 8,
ce qui explique pourquoi l'exécution de `"TestGame.java"` manque d'affichage après le commit de l'exo 8.

## Remarques
J'ai géré la plupart des erreurs sans utiliser de `try/catch` mais plus-tôt par des clauses `if/else` en essayant d'anticiper au maximum les erreurs possibles dans des 
conditions d'utilisations "normales" du jeu. Les erreurs classiques que je traite ainsi sont :
* Le tir en dehors de la grille
* Le placement de bateau en dehors de la grille
* Le tir sur une case déjà désignée précédemment
* La superposition de bateaux

Toutes les autres erreurs/exceptions sont en général déjà traitées dans le squelette fourni.

J'ai utilisé la valeur `null` des instances comme valeur frauduleuse dans de nombreux cas (pour `hit` notamment). 

J'ai également commenté la ligne `"sin.close"` à la fin de la boucle de la méthode `Game.run()` car le scanner n'étant pas utilisé, `sin` valait `null` et donnait une 
exception de pointeur null à la fin de chaque partie. 























