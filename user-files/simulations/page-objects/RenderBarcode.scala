import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object RenderBarcode {
   val userString = "{\"ticket_ids\":[\"${ticket_id}\"]}"

   val renderBarcode = http("Render Barcode")
      .post("/api/render-ticket?_format=json")
      .header("content-type", "application/json")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(200))
      .check(jsonPath("$..barcode_url").saveAs("barcode_url"))
      .check(jsonPath("$..passbook_url").saveAs("passbook_url"))
        
   val render = scenario("Render Barcode")
      .doIf(session => session("Event_id").as[String] != "-1") {
         exec(LoadEvent.getEvent)
         .foreach("${RenderTicketIds}", "ticket_id") {
            exec(GetToken.getToken)
            .exec(renderBarcode).pause(2)//.exec(GetBarcodeImage.getBarcode).exec(GetApplePassbook.getPassbook)
         }      
      }
}