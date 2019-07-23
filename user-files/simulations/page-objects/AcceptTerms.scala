import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AcceptTerms {
	val userString = "{\"response\":true,\"version\":\"${version}\"}"
	val sentHeaders = Map("Content-Type" -> "application/json", "x-csrf-token" -> "${token}")
  	val acceptTermsHttp = http("Accept Terms")
                        .post("/api/accept/terms?_format=json")
                        .header("content-type", "application/json")
                        .header("x-csrf-token", "${token}")
                        .body(StringBody(userString))
                        .check(status.is(200))

  	val acceptTerms = scenario("Accept Terms")
                .exec(acceptTermsHttp).pause(2)
}