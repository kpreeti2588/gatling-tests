import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object ReclaimTicket {
 //  val userString = "[{\"transfer_id\":\"${transferId}\"}]"
   val userString = "{}"

   val reclaimTicket = http("Transfer Ticket")
    //  .delete("/api/ticket/multiple-reclaim?_format=json")
      .delete("/api/v2/members/transfers/${transferId}?_format=json&time")
      .header("content-type", "application/json")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(200))

   val reclaim = scenario("Transfer Ticket")
         .exec(GetToken.getToken)
         .exec(reclaimTicket).pause(2)
      }