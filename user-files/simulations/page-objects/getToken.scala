import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetToken {
	val getTokenHttp = http("Get Token")
	                    .get("/rest/session/token?_format=json")
                        .check(status.is(200))
                        .check(bodyString.saveAs("token"))

  	val getToken = scenario("Get Token").exec(getTokenHttp).pause(2)
}