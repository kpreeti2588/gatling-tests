#!/usr/bin/env bash

ROOT=`pwd`
docker run -it --rm --name gatling \
    -e JAVA_OPTS="$2" \
    -v ${ROOT}/conf:/opt/gatling/conf \
    -v ${ROOT}/results/:/opt/gatling/results \
    -v ${ROOT}/user-files/:/opt/gatling/user-files \
    tmhub.io/aws/gatling:2.2.4 \
    -s $1