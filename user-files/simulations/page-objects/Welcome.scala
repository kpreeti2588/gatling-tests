import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadWelcome {
	val getWelcomeResources = http("Welcome")
                        .get("/ContentWelcome#")
                        .check(status.is(200))
                        
                        
	val getWelcome = scenario("Welcome")
     .exec(getWelcomeResources).pause(2)
}