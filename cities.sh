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
10)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille")
  ;;

20)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne")
  ;;

30)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt")
  ;;

40)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing")
  ;;

50)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes")
  ;;

60)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais")
  ;;

70)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand")
  ;;

80)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac")
  ;;

90)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais")
  ;;

100)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon")
  ;;

110)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny")
  ;;

120)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge")
  ;;

130)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain")
  ;;

140)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc")
  ;;

150)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "chalons-en-champagne" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete")
  ;;

160)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "noisy-le-sec" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete"
  "les-sables-d-olonne" "istres" "caluire-et-cuire" "talence" "garges-les-gonesse" "la-courneuve" "angouleme" "boulogne-sur-mer" "le-cannet" "bourg-en-bresse")
  ;;

170)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "noisy-le-sec" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete"
  "les-sables-d-olonne" "istres" "caluire-et-cuire" "talence" "garges-les-gonesse" "la-courneuve" "angouleme" "boulogne-sur-mer" "le-cannet" "bourg-en-bresse"
  "wattrelos" "castres" "thionville" "bron" "arras" "gap" "reze" "tarbes" "compiegne" "melun")
  ;;

180)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "noisy-le-sec" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete"
  "les-sables-d-olonne" "istres" "caluire-et-cuire" "talence" "garges-les-gonesse" "la-courneuve" "angouleme" "boulogne-sur-mer" "le-cannet" "bourg-en-bresse"
  "wattrelos" "castres" "thionville" "bron" "arras" "gap" "reze" "tarbes" "compiegne" "melun"
  "draguignan" "ales" "bagneux" "stains" "marcq-en-baroeul" "gagny" "chartres" "anglet" "coulomiers" "montelimar")
  ;;

190)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "noisy-le-sec" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete"
  "les-sables-d-olonne" "istres" "caluire-et-cuire" "talence" "garges-les-gonesse" "la-courneuve" "angouleme" "boulogne-sur-mer" "le-cannet" "bourg-en-bresse"
  "wattrelos" "castres" "thionville" "bron" "arras" "gap" "reze" "tarbes" "compiegne" "melun"
  "draguignan" "ales" "bagneux" "stains" "marcq-en-baroeul" "gagny" "chartres" "anglet" "coulomiers" "montelimar"
  "saint-martin-d-heres" "pontault-combault" "villefranche-sur-saone" "poissy" "chatillon" "villepinte" "savigny-sur-orge" "montlucon" "franconville" "bagnolet")
  ;;

200)
  tab=("paris" "marseille" "lyon" "toulouse" "nice" "nantes" "montpellier" "strasbourg" "bordeaux" "lille"
  "rennes" "reims" "saint-etienne" "le-havre" "toulon" "grenoble" "dijon" "angers" "nimes" "villeurbanne"
  "aix-en-provence" "le-mans" "clermond-ferrant" "brest" "tours" "amiens" "limoges" "annecy" "perpignan" "boulogne-billancourt"
  "metz" "besancon" "orleans" "argenteuil" "rouen" "mulhouse" "montreuil" "caen" "nancy" "tourcoing"
  "roubaix" "nanterre" "vitry-sur-seine" "avignon" "creteil" "dunkerque" "poitiers" "aubervilliers" "asnieres-sur-seine" "colombes"
  "versailles" "aulnay-sous-bois" "courbevoie" "cherbourg-en-cotentin" "rueil-malmaison" "champigny-sur-marne" "pau" "beziers" "la-rochelle" "calais"
  "saint-maur-des-fosses" "antibes" "cannes" "merignac" "drancy" "colmar" "saint-nazaire" "issy-les-moulineaux" "evry-courcouronnes" "noisy-le-grand"
  "bourges" "venissieux" "la-seyne-sur-mer" "cergy" "levallois-perret" "quimper" "valence" "villeneuve-d-ascq" "antony" "pessac"
  "clichy" "ivry-sur-seine" "troyes" "neuilly-sur-seine" "montauban" "chambery" "niort" "sarcelles" "lorient" "beauvais"
  "le-blanc-mesnil" "hyeres" "epinay-sur-seine" "villejuif" "pantin" "maisons-alfort" "saint-quentin" "meau" "chelles" "la-roche-sur-yon"
  "cholet" "narbonne" "fontenay-sous-bois" "vannes" "bondy" "frejus" "arles" "satrouville" "clamart" "bobigny"
  "corbeil-essone" "sevran" "bayonne" "grasse" "massy" "cagnes-sur-mer" "vincennes" "saint-ouen-sur-seine" "laval" "montrouge"
  "albi" "belfort" "evreux" "martigues" "suresnes" "vaulx-en-velvin" "brive-la-gaillarde" "charleville-mezieres" "gennevilliers" "saint-herblain"
  "saint-malo" "carcassonne" "saint-priest" "blois" "salon-de-provence" "chalon-sur-saone" "rosny-sous-bois" "meudon" "aubagne" "saint-brieuc"
  "noisy-le-sec" "puteaux" "livry-gargan" "choisy-le-roi" "chateauroux" "saint-germain-en-laye" "mantes-la-jolie" "alfortville" "valanciennes" "sete"
  "les-sables-d-olonne" "istres" "caluire-et-cuire" "talence" "garges-les-gonesse" "la-courneuve" "angouleme" "boulogne-sur-mer" "le-cannet" "bourg-en-bresse"
  "wattrelos" "castres" "thionville" "bron" "arras" "gap" "reze" "tarbes" "compiegne" "melun"
  "draguignan" "ales" "bagneux" "stains" "marcq-en-baroeul" "gagny" "chartres" "anglet" "coulomiers" "montelimar"
  "saint-martin-d-heres" "pontault-combault" "villefranche-sur-saone" "poissy" "chatillon" "villepinte" "savigny-sur-orge" "montlucon" "franconville" "bagnolet"
  "sainte-genevieve-des-bois" "echirolles" "creil" "tremblay-en-france" "conflans-sainte-honorine" "la-ciotat" "saint-chamond" "saint-raphael" "thonon-les-bains" "annemasse")
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
