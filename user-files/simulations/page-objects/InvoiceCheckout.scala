import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object InvoiceCheckout {
   val userString = "{\"invoice_id\":\"${invoice_id}\"}"

   val checkouthttp = http("Invoice Checkout")
      .post("/api/checkout?_format=json")
      .header("content-type", "application/json")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(200))

   val checkout = scenario("Invoice Checkout")
         .exec(GetToken.getToken)
         .exec(checkouthttp).pause(2)
}