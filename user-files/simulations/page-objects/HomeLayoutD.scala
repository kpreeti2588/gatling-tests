import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HomePageLayoutD {
	val getHomePageLayoutD = http("HomePage Layout D")
                        .get("/HomePageLayoutD#")
                        .check(status.is(200))
                        
                        
	val getHomeLayoutD = scenario("HomePage Layout D")
     .exec(getHomePageLayoutD).pause(2)
}