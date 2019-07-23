import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._


object VenueConfigUpdateRequest {

  //def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString	
  
  //val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
     //  "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";

  val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";

       //println(userString)
  

  val getVenueConfigurationData = http("venueConfiguration")
    .post(Environment.VVAPI+"/v2/venueConfiguration/relocation")
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString))
    .check(status.is(200))

}