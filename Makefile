build:
	javac -classpath "lib/*.jar":"src/*.java" -d bin src/*.java src/algos/*.java src/gui/*.java
run:
	java -classpath bin Main
run-gui:
	java -classpath bin Main --gui
