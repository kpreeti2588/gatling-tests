import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object BulkTransfer {
   val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))
//   val userString = "{\"event\": {\"event_id\": \"${Event_id}\"}, \"is_display_price\": true, \"ticket_ids\": [\"${TransferTicketId}\"],\"recipient\":{\"email\":\"kk@kk.com\",\"first_name\":\"KK\",\"last_name\":\"kk\"}}"
   val userString = "{\"invites\":[{\"seatIds\":[\"${TransferTicketId}\"],\"recipient\":{\"email\":\""+"${email}"+"\",\"firstName\":\"LoadFirst\",\"lastName\":\"LoadLast\"},\"note\":\"Load Test Tickets\"}]}"
   val bulkTransfers = http("Transfer Ticket V2")
      .post("/api/v2/members/transfers?_format=json&time")
      .header("content-type", "application/json")
      .header("TMPS-Correlation-Id", "${uuid}")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(201))
      .check(jsonPath("$.invites[*].id").saveAs("transferId"))

   val bulkClaim = scenario("Transfer Ticket V2")
      .feed(uuids)
      .doIf(session => session("Event_id").as[String] != "-1") {
          exec(GetToken.getToken)
         .exec(bulkTransfers).pause(5).exec(Logout.logout).exec(LoginAndClaim.claim)
      } 
}