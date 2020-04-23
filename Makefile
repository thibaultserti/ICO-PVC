build:
	javac -classpath "lib/*.jar":"src/*.java" -d bin src/*.java src/algos/*.java src/gui/*.java src/conf/*.java src/tests/*.java
run:
	java -classpath bin Main
run-gui:
	java -classpath bin Main --gui
run-tests:
	java -classpath bin Main --benchmark 1
dataset:
	java -classpath bin Main --dataset 100
clean:
	rm data/ag.csv data/rs.csv data/tabu.csv data/dataset.csv python/results/*

