import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TriggerEmailWest {

	var csvFeeder = csv(Environment.csv).circular;
    if(Environment.runType == "unique"){
    	csvFeeder = csv(Environment.csv).queue;
    }
    

	val userString = "{\"email\":\""+"${email}"+"\"}"


	val testEmailHttp = http("Test Email")
	                    .post("/api/send/email?_format=json")
	                    .header("content-type", "application/json")
      				      	.header("x-csrf-token", "g166BxYlUdaP1ovzaA5cIt3aOgOQQg6eao2DeoDws-c")
	                    .body(StringBody(userString))
                        .check(status.is(200))
                        .check(regex("Your email has been sent successfully").exists)

  	val triggerEmail = scenario("Trigger Email").exec(feed(csvFeeder)).exec(testEmailHttp).pause(2)
}