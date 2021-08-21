#!/bin/zsh
javac -d ./bin ./src/**/*.java && java -cp ./bin App ./static/scenario.txt