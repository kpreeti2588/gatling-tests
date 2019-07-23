To run locally,<br/>

./run-local.sh  RenderTicket "-Dthreads=1 -Dblocks=1 -Drampup=1 -DrunType=unique -Dhost=https://tm-am-stg.io-media.com/genesis"<br/>

./run-local.sh  RenderTicket "-Dthreads=50 -Dblocks=5 -Drampup=5 -Dduration=180 -Ddelay=10 -DrunType=circular -Dthrottle=49 -Dhost=https://tm-am-stg.io-media.com/genesis" <br/>

To run using terraform,

Run CI/CD pipeline of terraform-gatling project

To run on flood-io,

Checkout the floodloadtest.scala, copy your code to this file and run it on Flood.io<br/><br/>
Steps:
1. Create zip file containing user-files folder
2. Upload same zip file with floodloadtest.scala
3. Select gatling test
4. Mention the java-opts
5. Run it

Request Count:<br/>
* Render 59 Requests per user with CF<br/>
* Render 21 Requests per user without CF<br/>
* TransferClaim 63 Requests per user with CF<br/>
* TransferClaim 26 Requests per user without CF<br/>
* CreateAccount 4 Requests per user<br/>
* TransferClaim 17 Requests per user<br/>

For any information, contact<br/>
Prateek Ladha&#60;prateek.ladha@ticketmaster.com&#62;<br/>
Navjyot Singh&#60;navjyot.singh@ticketmaster.com&#62;