import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object LoginAndClaim {
    
    val repeats : Int = Integer.getInteger("repeats", 1);
    val postBody = "{\"name\":\""+"${email}"+"\",\"pass\":\""+"${pass}"+"\",\"remember_me\":1}"

    val loginHttp = http("Login New User")
      .post("/user/login?_format=json")
      .body(StringBody(postBody))
      .check(status.is(200))
      .check(bodyString.saveAs("RESPONSE_DATA"))
      .check(jsonPath("$..is_terms_of_use_acceptance_required").saveAs("is_terms_of_use_acceptance_required"))
    
    val claim = scenario("Login New User").repeat(repeats) {
      exec(loginHttp).pause(2).exec(GetTerms.getTerms).exec(GetToken.getToken).exec(ClaimTicket.claim)
    }
}