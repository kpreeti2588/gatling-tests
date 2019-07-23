import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EventsSummary {
  val geteventSummaryHttp = http("Events Summary")
                        .get("/api/user-events/summary?_format=json")
                        .check(status.is(200))

  val getEventSummary = scenario("Events Summary")
 		.exec(geteventSummaryHttp).pause(2)
}