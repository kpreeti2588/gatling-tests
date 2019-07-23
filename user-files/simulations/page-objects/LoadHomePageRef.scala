import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadHomePageReferer {
	val getHomePageResources = http("Referer HomePage")
                        .get("/")
                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","stg1.gameday.us-east-1.ppub-tmaws.io")
                        .check(status.is(200))
                        //.check(css("#mainNav").exists)
                        //.check(regex("""<title>Game Day</title>"""))
                        
	val getHomePage = scenario("Referer HomePage")
     .exec(getHomePageResources).pause(2)
}