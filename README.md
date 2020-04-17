**ICO - Problème du voyageur de commerce**

Le but est de programmer les 3 algorithmes Tabou, Recuit Simulé et Génétique et de les faire coopérer au moyen d'agent.

__Utilisation :__

__Interface graphique :__

Il y a deux modes pour lancer le programme : en ligne de commande ou avec l'interface graphique.
Pour lancer le programme en mode graphique, il faut utiliser l'option `--gui`
Pour le lancer en mode ligne de commande, aucune option n'est nécessaire.

__Tests :__

Pour créer un fichier de benchmark, il faut lancer le programme avec l'option `--benchmark`
Cela génère des fichiers `ag.csv`, `rs.csv`, `tabu.csv` qu contiennent dans cet ordre : 
le nombre de villes, la distance optimale trouvée et le temps d'exécution

__Villes :__

La récupération des villes se fait avec `cities.sh`. 
Une fois exécuté, les villes sont enregistrées dans le fichier `cities.csv`.
Il est lui-même utilisé pour créer les objets Java par la classe Main.

Pour essayer avec d'autres villes, il suffit de mettre à jour le tableau des villes dans `cities.sh` et d'exécuter le programme avec la commande `bash cities.sh`.
L'option `--size` ou `-s` permet de spécifier le nombre de villes à utiliser (les multiples de 10 jusqu'à 200).

__Pour compiler et exécuter :__

_Linux :_
Depuis un terminal, placez-vous dans le dossier principal du projet (où le fichier `Makefile` est présent) et lancez `make build` pour compiler, puis `make run` pour exécuter en ligne de commande et `make run-gui` pour exécuter avec l'interface graphique.


_Windows :_
Depuis un terminal, placez-vous dans le dossier principal du projet et lancez : `javac -classpath "lib/*.jar":"src/*.java" -d bin src/*.java src/algos/*.java src/gui/*.java`
Ensuite lancez `java -classpath bin Main` pour exécuter le programme en ligne de commande et `java -classpath bin Main --gui` pour exécuter avec une interface graphique.

