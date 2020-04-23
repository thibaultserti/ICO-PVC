build:
	javac -classpath lib/jade/*.jar -d bin src/*.java src/algos/*.java src/gui/*.java src/conf/*.java src/tests/*.java src/sma/*.java
run:
	java -classpath bin Main
run-gui:
	java -classpath bin Main --gui
run-tests:
	java -classpath bin Main --benchmark 1
dataset:
	java -classpath bin Main --dataset 100
agent:
	java -classpath bin/:lib/jade/jade.jar:src/ jade.Boot -agents "ag:sma.AgentAG;rs:sma.AgentRS;tabu:sma.AgentTabu"
agent-gui:
	java -classpath bin/:lib/jade/jade.jar:src/ -gui jade.Boot -agents "ag:sma.AgentAG;rs:sma.AgentRS;tabu:sma.AgentTabu"
clean:
	rm data/ag.csv data/rs.csv data/tabu.csv data/dataset.csv python/results/*