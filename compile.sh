printf "\e[31m! Compiling... !\e[0m\n"

javac -d bin ./*.java
jar cfm chirp!.jar manifest.txt -C bin .
java -jar chirp!.jar
