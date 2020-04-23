# ICO - Problème du voyageur de commerce

Le but est de programmer les 3 algorithmes Tabou, Recuit Simulé et Génétique et de les faire coopérer au moyen d'agents.

## Utilisation :

### Interface graphique :

Il y a deux modes pour lancer le programme : en ligne de commande ou avec l'interface graphique.
Pour lancer le programme en mode graphique, il faut utiliser l'option `--gui`
Pour le lancer en mode ligne de commande, aucune option n'est nécessaire.

### Villes :

La récupération des villes se fait avec `cities.sh`. 
Une fois exécuté, les villes sont enregistrées dans le fichier `cities.csv`.
Il est lui-même utilisé pour créer les objets Java par la classe Main.

Pour essayer avec d'autres villes, il suffit de mettre à jour le tableau des villes dans `cities.sh` et d'exécuter le programme avec la commande `bash cities.sh`.
L'option `--size` ou `-s` permet de spécifier le nombre de villes à utiliser (les multiples de 10 jusqu'à 200).

### Tests :

Pour créer un fichier de benchmark, il faut lancer le programme avec l'option `--benchmark n`, n étant le nombre 
de tests que l'on veut faire pour chaque possibilité de lancement.
Cela génère des fichiers `ag.csv`, `rs.csv`, `tabu.csv` qu contiennent dans cet ordre : 
le nombre de villes, la distance optimale trouvée et le temps d'exécution.
Ensuite, il est possible de générer des courbes et des tableaux récapitulatifs avec le script python,
qu'il suffit d'exécuter avec `python3 python/plots.py`

**Attention :** le temps d'exécution est déjà assez long pour n=1, cela prend déjà 5 minutes. (à cause de Tabu)

### Dataset :

Pour créer le dataset, il faut lancer le programme avec l'option `--dataset n`, n étant le nombre d'entrée 
voulu pour chaque algorithme dans le dataset (finalement le nombre de ligne du dataset sera donc de 3*n) 

### Système multi-agents

Afin d'améliorer les performances nous avons mis en place un système multi-agent grâce à la plateforme JADE.
Pour la tester, il suffit d'exécuter `make agent` ou bien `make agent-gui`.

## Pour compiler et exécuter :

### Linux :

Dans un premier temps il faut installer `jq` car il est utilisé dans le script de récupération de villes.
Sous Debian, Ubuntu ou un dérivé : `apt install jq`.
Depuis un terminal, placez-vous dans le dossier principal du projet (où le fichier `Makefile` est présent) et lancez `make build` pour compiler, puis `make run` pour exécuter en ligne de commande et `make run-gui` pour exécuter avec l'interface graphique.
Pour lancer les tests lancer : `make run-tests`.
Pour créer le dataset : `make dataset`.
Pour supprimer tous les csv et png générés : `make clean`

### Windows :

Depuis un terminal, placez-vous dans le dossier principal du projet et lancez : `javac -classpath "lib/*.jar":"src/*.java" -d bin src/*.java src/algos/*.java src/gui/*.java`
Ensuite lancez `java -classpath bin Main` pour exécuter le programme en ligne de commande et `java -classpath bin Main --gui` pour exécuter avec une interface graphique.

