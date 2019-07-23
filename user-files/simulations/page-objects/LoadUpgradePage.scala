import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadUpgradePage {
	val getUpgradePageResources = http("Load Upgrade Page")
                        .get("/upgrade")
                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","")
  											.check(status.is(200))
                          //header("location").saveAs("nextPageLocation"))
                      //  .check(status.in(200,304))

                        //.check(css("#mainNav").exists)
                        //.check(regex("<title>Game Day</title>"""))

	//val getRedirectPageResources = http("Redirect Page")
	//											.get("${nextPageLocation}")
	//											.check(css("#MainPart_lbUsersInLineAheadOfYou", "value").find.saveAs("queue_number"))

	val getUpgradePage = scenario("Load Upgrade Page")
   .exec(GetToken.getToken)
     .exec(getUpgradePageResources).pause(15)
  	// .exec(getRedirectPageResources).pause(15)


}
