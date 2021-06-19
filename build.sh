#!/bin/bash
source ~/.profile
mvn clean install -DskipTests=true
docker stop $(docker ps -aq)
sleep 1
docker rm $(docker ps -q -f status=exited)
sleep 1
docker-compose stop
sleep 2
docker-compose up -d --build --force-recreate
sleep 2
docker ps