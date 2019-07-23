import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object CreateAccount {
    val randoms = Iterator.continually(
      Map("random" -> Random.nextInt(Integer.MAX_VALUE))
    )
    
    val userString = "{\"first_name\":\"FirstTest\",\"last_name\":\"LastTest\",\"email\":\"gattest"+"${random}"+"@example.com\",\"password\":\"123456\",\"remember_me\":0}"

    val createNewUser = http("Create Account")
        .post("/user/register?_format=json")
        .body(StringBody(userString))
        .check(status.is(200))
        .check(bodyString.saveAs("RESPONSE_DATA"))
        .check(jsonPath("$..is_terms_of_use_acceptance_required").saveAs("is_terms_required"))
    
    val createUser = scenario("Create Account")
        .feed(randoms)
        .exec(createNewUser).pause(2).exec(GetTerms.getTerms).pause(2)
}