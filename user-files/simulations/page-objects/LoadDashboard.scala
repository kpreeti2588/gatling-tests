import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadDashboard {
	val getDashboardResources = http("Load Dashboard")
                        .get("/dashboard")
                        .check(status.in(200,304))
                        
	val getDashboard = scenario("Load Dashboard")
     	.exec(getDashboardResources).pause(2)
}