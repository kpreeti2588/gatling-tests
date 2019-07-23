import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object EditorCreation {
    var csvFeeder = csv(Environment.csv).circular;
    if(Environment.runType == "unique"){
        csvFeeder = csv(Environment.csv).queue;
    }
    
    val repeats : Int = Integer.getInteger("repeats", 1);
    val postBody = "{\"email\":\""+"${email}"+"\"}"

    val UserCreate = http("Editor User Creation")
      .post("/iom-ppsync/create/admin-user")
      .header("validation-token", "47fb59eb96935770ebba311bc02db703289ffeb2a745ccf51ca89a297f47e382")
      .body(StringBody(postBody))
      .check(status.is(200))
      .check(bodyString.saveAs("RESPONSE_DATA"))
    
    val CMSUserCreate = scenario("Editor User Creation").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(UserCreate).pause(2)
    }
}
