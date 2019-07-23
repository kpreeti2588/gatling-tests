import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object CMSUser1 {
    var csvFeeder = csv(Environment.csv).circular;
    if(Environment.runType == "unique"){
        csvFeeder = csv(Environment.csv).queue;
    }
    
    val repeats : Int = Integer.getInteger("repeats", 1);
    val postBody = "{\"name\":\""+"${email}"+"\"}"

    val ForgotPassword = http("CMS User Forgot Password")
      .post("/user/password")
      .body(StringBody(postBody))
      .check(status.is(200))
      .check(bodyString.saveAs("RESPONSE_DATA"))
    
    val CMSForgotPassword = scenario("CMS User Forgot Password").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(ForgotPassword).pause(2)
    }
}
