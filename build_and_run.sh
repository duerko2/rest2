#!/bin/bash
set -e
pushd code-with-quarkus
mvn package
docker-compose build
docker-compose up -d
docker image prune -f
popd

sleep 2

pushd client-code-updated
mvn test
popd