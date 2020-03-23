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

case $size in
7*)
  tab=("paris" "lille" "strasbourg" "lyon" "marseille" "bordeaux" "brest")
  ;;
15*)
  tab=("paris" "lille" "nancy" "strasbourg" "dijon" "lyon" "grenoble" "nice" "marseille" "montpellier" "toulouse"
    "bordeaux" "nantes" "brest" "rennes")
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

name="cities${#tab[@]}.csv"

if [ ! -e "$name" ]; then
  rm cities.csv
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
cp "$name" cities.csv
