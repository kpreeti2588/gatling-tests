import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EmailInvoiceSummary {
	val getInvoiceEmail = http("Invoice Summary Email")
            .get("/api/invoice/email/${invoice_id}?_format=json")
            .check(status.in(200))
            .check(jsonPath("$.heading").is("Your Invoice has been sent!"))
                        
	val getSummaryEmail = scenario("Invoice Summary Email")
	     	.exec(getInvoiceEmail).pause(2)
}