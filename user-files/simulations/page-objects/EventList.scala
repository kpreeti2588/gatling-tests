import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object EventList {
  	val getEventListHttp = http("Event List")                 
                    	  .get("/api/user-events/listing?_format=json")
                        .check(status.is(200))
                        .check(bodyString.saveAs("Event_Response"))
                        .check(jsonPath("$[*].event_id").findAll.saveAs("Event_Ids"))
                        .check(jsonPath("$[*].event_id").count.saveAs("EventsCount"))

                        
  	val getEventList = scenario("Event List")
		.exec(getEventListHttp).pause(2)
}