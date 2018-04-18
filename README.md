# Mini Projet : un programme RMI en java
## Auteurs
[Antoine Dezarnaud](https://github.com/antoined73)

[Elliot Kauffmann](https://github.com/dratinyy/)
## Contenu
Dans le cadre d'un projet pour l'option Réseaux Avancés et Intergiciels, Poltech'Nice-Sophia, Département Sciences de l'Informatique, 4ème année.

Le sujet du projet est disponible [ici](https://lms.unice.fr/mod/assign/view.php?id=1531).
La thèse sur laquelle est basée le projet est disponible [ici](https://tel.archives-ouvertes.fr/tel-01166047/document).
## Utilisation
Pour tester ce projet, il suffit de lancer au minimum 1 producteur et 1 consommateur :
1) **Producteur :** Executer la classe PClientLauncher. (Ne pas l'executer plus d'une fois à la fois).
2) **Consommateur :** Executer la classe CClientLauncher avec son nom en argument. (Notez que si aucun argument n'est donné, il sera tout de même demandé au lancement). Si vous désirez avoir plusieurs clients qui écoutent le producteur simultanément, il suffit d'executer la classe CClientLauncher plusieurs fois avec différents noms.


## Implémentation
Le package **common** inclus les interface des services utilisés par les producteurs et consommateurs. On y déclare deux services. Le premier, ConsumerConnectionService, est appelé par le consommateur auprès du producteur lorsqu'il souhaite que ce dernier l'ajoute à sa liste interne de consommateurs à notifier. Le second, PrintMessageService, est appelé par le producteur auprès des consommateurs qui sont inscrits lorsqu'une nouvelle donnée est lue, pour leur être envoyée.

Le package **consumer** contient la classe *consumer* ainsi qu'un *launcher* qui permet d'en instancier un nombre d'instances, avec un nom paramétrable (celui-ci peut être passé en paramètre, ou indiqué dans l'entrée standard).

Le package **producer** contient la classe *producer* ainsi qu'un *launcher* qui permet d'en instancier un. Le producteur doit être lancé avant les différents consommateurs, car il crée le registre auprès duquel tous les objets distants sont passés.

Enfin, dans le dossier **data**, on peut trouver le fichier qui sera lu en boucle par le producteur et passé aux consommateurs.