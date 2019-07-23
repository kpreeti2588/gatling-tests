gatlnig tests

To run locally,
./run-local.sh  RenderTicket "-Dthreads=1 -Dblocks=1 -Drampup=1 -DrunType=unique -Dhost=https://tm-am-stg.io-media.com/genesis"
./run-local.sh  RenderTicket "-Dthreads=50 -Dblocks=5 -Drampup=5 -Dduration=180 -Ddelay=10 -DrunType=circular -Dthrottle=49 -Dhost=https://tm-am-stg.io-media.com/genesis" 
To run using terraform,
Run CI/CD pipeline of terraform-gatling project
To run on flood-io,
Checkout the floodloadtest.scala, copy your code to this file and run it on Flood.io
Steps:

Create zip file containing user-files folder
Upload same zip file with floodloadtest.scala
Select gatling test
Mention the java-opts
Run it

Request Count:

Render 59 Requests per user with CF

Render 21 Requests per user without CF

TransferClaim 63 Requests per user with CF

TransferClaim 26 Requests per user without CF

CreateAccount 4 Requests per user

TransferClaim 17 Requests per user
