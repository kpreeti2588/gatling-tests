import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadEvent {
	val getEventResources = http("Load MyEvent")
			//.get("/tickets#/${Event_id}")
            .get("/myevents#/${Event_id}")
            .check(status.in(200, 304))
                        
	val getEvent = scenario("Load MyEvent")
	     	.exec(getEventResources).pause(2)
}