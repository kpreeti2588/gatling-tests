import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object VenueAvailabilityPostRequest {


  //val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"memberId\":\"${member_id}\"}}";

 //Anonymous users 
 val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";

  println(userString)

  val getVenueAvailabilityData = http("venueAvailability")
    .post(Environment.VVAPI+"/v2/venueAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString))
    .check(status.is(200))

  val userString2 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"promoCode\":\"" + Environment.promoCode + "\",\"memberId\":\"${member_id}\"}}";

    println(userString2)

  val getVenueAvailabilityDataUsingPromocode = http("venueAvailabilityWithPromocode")
    .post(Environment.VVAPI+"/v2/venueAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString2))
    .check(status.is(200))
}
