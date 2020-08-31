#!/bin/bash

find . -name "*.class" -exec rm {} \;
rm -f simulation.txt

javac wethinkcode/simulator/Simulator.java

java wethinkcode.simulator.Simulator scenario.txt