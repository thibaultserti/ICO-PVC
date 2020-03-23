#!/usr/bin/env bash
# Le tableau doit être dans l'ordre optimal pour les distances
tab=("paris" "lille" "strasbourg" "lyon" "marseille" "bordeaux" "brest")

rm cities.csv
echo "ville,x,y" >> cities.csv

for city in ${tab[*]}; do
  echo "Téléchargement $city ..."
  curl "https://api-adresse.data.gouv.fr/search/?q=$city" >city.json
  x=$(jq '.features[0] .geometry .coordinates[0]' city.json)
  y=$(jq '.features[0] .geometry .coordinates[1]' city.json)
  echo "$city,$x,$y" >> cities.csv
done
rm city.json

echo "Toutes les villes ont été téléchargées correctement ! "
