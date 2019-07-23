import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object VenueAvailabilityUpgradeRequest {

  //def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString


 // val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
    //   "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"memberId\":\"" + Environment.memberId + "\"}}";

 val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";

 // println(userString)

  val getVenueAvailabilityData = http("venueAvailability")
   //.post(Environment.VVAPI+"/v2/venueAvailability/relocation")
    .post("/v2/venueAvailability/relocation") 
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString))
    .check(status.is(200))

  val userString2 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"promoCode\":\"" + Environment.promoCode + "\",\"memberId\":\"${member_id}\"}}";

 // println(userString2)

  val getVenueAvailabilityDataUsingPromocode = http("venueAvailabilityWithPromocode")
    .post(Environment.VVAPI+"/v2/venueAvailability/relocation")
    //.post("/v2/venueAvailability/relocation") 
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString2))
    .check(status.is(200))
}
