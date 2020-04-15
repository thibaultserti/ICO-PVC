#!/usr/bin/env bash

while [ -n "$1" ]; do
  case "$1" in
  --size | -s)
    shift
    size=$1
    ;;
  esac
  shift
done
# Choix des villes en fonctions du nombre de villes voulus

case $size in
5*)
  tab=("paris" "lille" "lyon" "marseille" "brest")
  ;;
6*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "brest")
  ;;
7*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "bordeaux" "brest")
  ;;
8*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "toulouse" "bordeaux" "brest")
  ;;
9*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "montpellier" "toulouse" "bordeaux" "brest")
  ;;
10*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "montpellier" "toulouse" "bordeaux" "nantes" "brest")
  ;;
11*)
  tab=("paris" "lille" "nancy" "strasbourg" "lyon" "marseille" "montpellier" "toulouse" "bordeaux" "nantes" "brest")
  ;;
12*)
  tab=("paris" "lille" "nancy" "strasbourg" "lyon" "marseille" "montpellier" "toulouse" "bordeaux" "nantes" "brest"
   "rennes")
  ;;
13*)
  tab=("paris" "lille" "nancy" "strasbourg" "lyon" "nice" "marseille" "montpellier" "toulouse" "bordeaux" "nantes"
  "brest" "rennes")
  ;;
14*)
  tab=("paris" "lille" "nancy" "strasbourg" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse"
    "bordeaux" "nantes" "brest" "rennes")
  ;;
15*)
  tab=("paris" "lille" "nancy" "strasbourg" "dijon" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse"
    "bordeaux" "nantes" "brest" "rennes")
  ;;
16*)
  tab=("orleans" "paris" "lille" "nancy" "strasbourg" "besancon" "dijon" "lyon" "grenoble" "nice" "marseille"
   "montpellier" "toulouse" "bordeaux" "nantes" "brest" "rennes")
  ;;
17*)
  tab=("paris" "lille" "nancy" "strasbourg" "dijon" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse"
   "bordeaux" "nantes" "brest" "rennes")
  ;;
18*)
  tab=("orleans" "paris" "lille" "nancy" "strasbourg" "besancon" "dijon" "lyon" "grenoble" "nice" "marseille"
  "montpellier" "toulouse" "bordeaux" "nantes" "brest" "rennes")
  ;;
19*)
  tab=("orleans" "paris" "lille" "nancy" "strasbourg" "besancon" "dijon" "lyon" "grenoble" "nice" "marseille"
   "montpellier" "toulouse" "bordeaux" "limoges" "nantes" "brest" "rennes")
  ;;
20*)
  tab=("orleans" "paris" "calais" "lille" "nancy" "strasbourg" "besancon" "dijon" "lyon" "grenoble" "nice"
   "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen")
  ;;
21*)
  tab=("orleans" "paris" "lille" "reims" "nancy" "strasbourg" "besancon" "dijon" "lyon" "grenoble" "nice" "marseille"
   "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen")
  ;;
22*)
  tab=("orleans" "paris" "lille" "reims" "nancy" "strasbourg" "besancon" "dijon" "clermond-ferrant" "lyon" "grenoble"
   "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen")
  ;;
23*)
  tab=("orleans" "paris" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon" "clermond-ferrant" "lyon"
   "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen")
  ;;
24*)
  tab=("orleans" "paris" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon""clermond-ferrant" "lyon"
   "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen"
    "le-mans")
  ;;
25*)
  tab=("orleans" "paris" "rouen" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon""clermond-ferrant" "lyon"
   "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges" "nantes" "brest" "rennes" "caen"
   "le-mans")
  ;;
26*)
  tab=("orleans" "paris" "rouen" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon"
    "clermond-ferrant" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges"
    "nantes" "brest" "rennes" "caen" "le-mans" "tours")
  ;;
27*)
  tab=("orleans" "paris" "rouen" "amiens" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon"
    "clermond-ferrant" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges"
    "nantes" "brest" "rennes" "caen" "le-mans" "tours")
  ;;
28*)
  tab=("orleans" "paris" "rouen" "amiens" "calais" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon"
    "clermond-ferrant" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges"
    "nantes" "brest" "rennes" "caen" "le-mans" "tours")
  ;;
29*)
  tab=("orleans" "paris" "rouen" "amiens" "calais" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon"
    "clermond-ferrant" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges"
    "la-rochelle" "nantes" "brest" "rennes" "caen" "le-mans" "tours")
  ;;
30*)
  tab=("orleans" "paris" "rouen" "amiens" "calais" "lille" "reims" "metz" "nancy" "strasbourg" "besancon" "dijon"
    "clermond-ferrant" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse" "pau" "bordeaux" "limoges"
    "la-rochelle" "nantes" "quimper" "brest" "rennes" "caen" "le-mans" "tours")
  ;;
*)
  exit
  ;;
esac

# Téléchargement des villes

name="data/cities${#tab[@]}.csv"

if [ ! -e "$name" ]; then
  rm data/cities.csv
  echo "ville,x,y" >>"$name"

  for city in ${tab[*]}; do
    echo "Téléchargement $city ..."
    curl "https://api-adresse.data.gouv.fr/search/?q=$city" >city.json
    x=$(jq '.features[0] .geometry .coordinates[0]' city.json)
    y=$(jq '.features[0] .geometry .coordinates[1]' city.json)
    echo "$city,$x,$y" >>"$name"
  done
  rm city.json

  echo "Toutes les villes ont été téléchargées correctement ! "
fi
cp "$name" data/cities.csv
