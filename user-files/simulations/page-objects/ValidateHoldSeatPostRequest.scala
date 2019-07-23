import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object ValidateHoldSeatPostRequest {

  val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \"" + Environment.language + "\",\"dataState\":" + Environment.dataState + ","+
  "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"seats\":["+
  "{\"seatPrice\":" + Environment.seatPrice1 + ",\"seatPriceCode\":\"" + Environment.seatPriceCode1 + "\",\"sectionName\":\"" + Environment.sectionName1 + "\","+
  "\"seatNum\":\"" + Environment.seatNum1 + "\",\"row\":\"" + Environment.row1 + "\",\"numSeat\":" + Environment.numSeat1 + ",\"ticketType\":\"" + Environment.ticketType1 + "\"}"+",{\"seatPrice\":" + Environment.seatPrice2 + ",\"seatPriceCode\":\"" + Environment.seatPriceCode2 + "\",\"sectionName\":\"" + Environment.sectionName2 + "\","+
  "\"seatNum\":\"" + Environment.seatNum2 + "\",\"row\":\"" + Environment.row2 + "\",\"numSeat\":" + Environment.numSeat2 + ",\"ticketType\":\"" + Environment.ticketType2 + "\"}"+
  "]}}"
  println(userString)
  val getValidateHoldSeatData = http("validateHoldSeat")
    .post(Environment.VVAPI+"/v1/validateHoldSeat/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", InitializePostRequest.randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString))
    .check(status.is(200))
}
