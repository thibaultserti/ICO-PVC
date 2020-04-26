#!/usr/bin/env bash

tab=("collaboration" "competition")

for e in ${tab[*]}; do
  for n in {10..200..10}; do
    for t in {1..10000..500}; do
      echo "\u[31m$e" - $n "/200" -  $t "/10000\u[0m"
      java -classpath bin/:lib/jade/jade.jar:src/ jade.Boot -agents "ag:sma.AgentAG($e,$n,$t);rs:sma.AgentRS($e,$n,$t);tabu:sma.AgentTabu($e,$n,$t)"
    done
  done
done
