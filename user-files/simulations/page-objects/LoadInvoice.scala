import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadInvoice {
  val  getInvoiceLoad = http("Load Invoice Page")
                        .get("/invoice")
                        .check(status.is(200))
                        
	val getInvoice = scenario("Load Invoice Page")
     .exec(getInvoiceLoad).pause(2)
}