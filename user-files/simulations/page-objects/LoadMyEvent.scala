import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadMyEvent {
	val getEventResources = http("Load MyEvents Page")
			//.get("/tickets#/${Event_id}")
            .get("/myevents#")
            .check(status.in(200, 304))
                        
	val getEvent = scenario("Load MyEvents Page")
	     	.exec(getEventResources).pause(2)
}