**ICO - Problème du voyageur de commerce**

Le but est de programmer les 3 algorithmes Tabou, Recuit Siulé et Génétique et de les faire coopérer au moyen d'agent.

__Utilisation:__

__Villes:__

La récupération des villes se fait avec `cities.sh`. 
Une fois exécuté, les villes sont enregistrées dans le fichier `cities.csv`.
Il est lui même utilisé pour créer les objets Java par la classe Main.

Pour essayer avec d'autres villes, il suffit de mettre à jour le tableau des villes dans `cities.sh` et d'exécuter le programme avec la commande `bash cities.sh`.

__Pour compiler et exécuter:__

_Linux:_
Depuis un terminal, placez-vous dans le dossier principal du projet (où le fichier `Makefile` est présent) et lancez `make build` pour compiler, puis `make run` pour exécuter.

_Windows:_
Depuis un terminal, placez-vous dans le dossier principal du projet et lancez : `javac -d bin src/*.java`
Ensuite, placez-vous dans le dossier `bin` avec `cd bin` (obligatoire) et lancez `java Main`. 
*(Remarque: `cd` et bien une commande DOS)*
