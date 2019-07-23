import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object BuyMemberEvents {

	val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))
    

  	val getMemberEventsHttp = http("Buy Member Events")                 
                    	  .get("/api/event-details?_format=json&time")
                    	  .header("TMPS-Correlation-Id", "${uuid}")
                        .check(status.is(200))
      

                       
  	val getAllEvents = scenario("Buy Member Events")
  		.feed(uuids)
		.exec(getMemberEventsHttp).pause(2)
}