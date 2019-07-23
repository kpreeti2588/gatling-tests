import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object TransferAndClaim {
//   val userString = "{\"event\": {\"event_id\": \"${Event_id}\"}, \"is_display_price\": true, \"ticket_ids\": [\"${TransferTicketId}\"],\"recipient\":{\"email\":\"kk@kk.com\",\"first_name\":\"KK\",\"last_name\":\"kk\"}}"
   val userString = "{\"events\":[{\"event\":{\"event_id\":\"${Event_id}\"},\"ticket_ids\":[\"${TransferTicketId}\"]}],\"is_display_price\":false,\"recipient\":{\"email\":\"testtransfer@test.com\",\"first_name\":\"Test\",\"last_name\":\"Transfer\"}}"
   val transferTicket = http("Transfer Ticket")
      .post("/api/ticket/transfer?_format=json")
      .header("content-type", "application/json")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(201))
      .check(jsonPath("$..transfer_id").saveAs("transferId"))

   val transferClaim = scenario("Transfer Ticket")
      .doIf(session => session("Event_id").as[String] != "-1") {
         exec(LoadEvent.getEvent)
         .exec(GetToken.getToken)
         .exec(transferTicket).pause(2).exec(Logout.logout).exec(LoginAndClaim.claim)
      } 
}