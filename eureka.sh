#!/usr/bin/env bash

fuser -k 8761/tcp
cd ../eureka
#mvn clean install

chmod +x ./target/fxquotes.eureka-1.0-SNAPSHOT.jar
java -jar ./target/*.jar