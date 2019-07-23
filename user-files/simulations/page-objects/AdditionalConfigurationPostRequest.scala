import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object AdditionalConfigurationPostRequest {

   def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString	
  
  val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";
 
  val getAdditionalConfigurationData = http("additionalConfiguration")
    .post(Environment.VVAPI+"/v1/additionalConfiguration/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString))
    .check(status.is(200))
}