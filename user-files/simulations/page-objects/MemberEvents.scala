import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object MemberEvents {

	val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))
    

  	val getMemberEventsHttp = http("Member Events")                 
                    	  .get("/api/v2/member/events?_format=json&time")
                    	  .header("TMPS-Correlation-Id", "${uuid}")
                        .check(status.is(200))
                        .check(bodyString.saveAs("Event_Response"))
                        .check(jsonPath("$.0.events[*].id").findAll.saveAs("Event_Ids"))
                        .check(jsonPath("$.0.events[*].id").count.saveAs("EventsCount"))

                       
  	val getAllEvents = scenario("Member Events")
  		.feed(uuids)
		.exec(getMemberEventsHttp).pause(2)
}