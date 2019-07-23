import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object ClaimTicket {
    
   val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))
   val sentHeaders = Map("Content-Type" -> "application/json", "x-csrf-token" -> "${token}")
   val userString = "{\"status\":\"accepted\"}"

   val claimTicket = http("Claim Ticket V2")
  //   .patch("/api/ticket/claim/accept/${transferId}?_format=json")
     .patch("/api/v2/member/invites/accept/${transferId}?_format=json")
     .header("content-type", "application/json")
     .header("TMPS-Correlation-Id", "${uuid}")
     .header("x-csrf-token", "${token}")
     .body(StringBody(userString))
     .check(status.is(204))
        
        
   val claim = scenario("Claim Ticket V2")
     .feed(uuids)
     .exec(claimTicket).pause(2)
}