build:
	javac -d bin src/*.java

run:
	cd bin ; java Main $(filter-out $@,$(MAKECMDGOALS)) ; cd ..