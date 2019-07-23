import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BuyMemberList {
  	val getEventHttp = http("Buy Member List")
                        .get("/api/v1/members/events/buy?_format=json&time")
                        .check(status is 200)
                        .check(jsonPath("$..event_name").optional.saveAs("event_name"))

  	val getEvent = scenario("Buy Member List")
	 	.exec(getEventHttp).pause(2)
}