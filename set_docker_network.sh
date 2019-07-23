#!/bin/bash
echo 'Starting Docker networks'
docker network create --driver bridge --subnet=192.168.33.0/24 --gateway=192.168.33.10 --opt "com.docker.network.bridge.name"="docker1" 3g
tc qdisc add dev docker1 root handle 1: htb default 12
tc class add dev docker1 parent 1:1 classid 1:12 htb rate 1.5mbit ceil 3mbit
tc qdisc add dev docker1 parent 1:12 netem delay 150ms

docker network create --driver bridge --subnet=192.168.34.0/24 --gateway=192.168.34.10 --opt "com.docker.network.bridge.name"="docker2" cable
tc qdisc add dev docker2 root handle 1: htb default 12
tc class add dev docker2 parent 1:1 classid 1:12 htb rate 7.5mbit ceil 15mbit
tc qdisc add dev docker2 parent 1:12 netem delay 14ms

docker network create --driver bridge --subnet=192.168.35.0/24 --gateway=192.168.35.10 --opt "com.docker.network.bridge.name"="docker3" 3gfast
tc qdisc add dev docker3 root handle 1: htb default 12
tc class add dev docker3 parent 1:1 classid 1:12 htb rate 1.6mbit ceil 1.6mbit
tc qdisc add dev docker3 parent 1:12 netem delay 75ms

docker network create --driver bridge --subnet=192.168.36.0/24 --gateway=192.168.36.10 --opt "com.docker.network.bridge.name"="docker4" 3gslow
tc qdisc add dev docker4 root handle 1: htb default 12
tc class add dev docker4 parent 1:1 classid 1:12 htb rate 0.4mbit ceil 0.4mbit
tc qdisc add dev docker4 parent 1:12 netem delay 200ms

docker network create --driver bridge --subnet=192.168.37.0/24 --gateway=192.168.37.10 --opt "com.docker.network.bridge.name"="docker5" 4g
tc qdisc add dev docker5 root handle 1: htb default 12
tc class add dev docker5 parent 1:1 classid 1:12 htb rate 3.75mbit ceil 7.5mbit
tc qdisc add dev docker5 parent 1:12 netem delay 2ms