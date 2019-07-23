import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object NewUserLogin {
    var csvFeeder = csv(Environment.csv).circular;
    if(Environment.runType == "unique"){
    	csvFeeder = csv(Environment.csv).queue;
    }
    
    val repeats : Int = Integer.getInteger("repeats", 1);
    val postBody = "{\"name\":\"gattest"+"${random}"+"@example.com\",\"pass\":\""+"123456"+"\",\"remember_me\":1}"

    val loginHttp = http("Login")
      .post("/user/login?_format=json")
      .body(StringBody(postBody))
      .check(status.is(200))
      .check(bodyString.saveAs("RESPONSE_DATA"))
      .check(jsonPath("$..is_terms_of_use_acceptance_required").saveAs("is_terms_of_use_acceptance_required"))
      .check(jsonPath("$..member_related_accounts[0].name").saveAs("NickName"))
    
    val login = scenario("Login").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(loginHttp).pause(2).exec(GetTerms.getTerms) 
    }
}