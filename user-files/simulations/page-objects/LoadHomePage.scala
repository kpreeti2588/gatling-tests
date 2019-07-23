import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadHomePage {
	val getHomePageResources = http("Load Home Page")
                        .get("/")
                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","")
                        .check(status.is(200))
                        //.check(css("#mainNav").exists)
                        //.check(regex("""<title>Game Day</title>"""))
                        
	val getHomePage = scenario("Load Home Page")
     .exec(getHomePageResources).pause(2)
}