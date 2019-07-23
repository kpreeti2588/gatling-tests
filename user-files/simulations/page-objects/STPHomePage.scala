import io.gatling.core.Predef._
import io.gatling.http.Predef._

object STPHomePage {
	val getHomePageResources = http("Load Home Page")
                        .get("")
                        .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36 5Lo1RZau")
                        //.header("Referer","")
                        .check(status.is(200))
                        //.check(css("#mainNav").exists)
                        //.check(regex("""<title>Game Day</title>"""))
                        
	val getHomePage = scenario("Load Home Page")
     .exec(getHomePageResources).pause(2)
}