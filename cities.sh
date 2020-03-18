#!/usr/bin/env bash
tab=("paris" "strasbourg" "lyon" "marseille" "bordeaux" "brest" "lille")

rm cities.csv
for city in ${tab[*]}; do
  curl "https://api-adresse.data.gouv.fr/search/?q=$city" >city.json
  x=$(jq '.features[0] .geometry .coordinates[0]' city.json)
  y=$(jq '.features[0] .geometry .coordinates[1]' city.json)
  echo "$city,$x,$y" >> cities.csv
done
rm city.json
