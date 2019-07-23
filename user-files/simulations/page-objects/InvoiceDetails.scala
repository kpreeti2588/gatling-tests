import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object InvoiceDetails {
  	val getInvoiceDetailsHttp = http("Invoice Details")                 
                    	 	.get("/api/invoice/${invoice_id}/1?_format=json")
                        	.check(status.is(200))
                        	.check(bodyString.saveAs("Invoice_Details"))
                        	.check(jsonPath("$.data[*].invoice_item_seq_id").findAll.saveAs("Invoice_Seq_Ids"))
                        	.check(jsonPath("$.list_item.payment_plan_id").optional.saveAs("plan_id"))
                      
                        
  	val getInvoiceDetails = scenario("Invoice Details")
		.exec(getInvoiceDetailsHttp).pause(5)
		.exec{session =>
            val seq_ids = session("Invoice_Seq_Ids").as[Vector[String]];
            val string = seq_ids.mkString(",");
            println("Seq Ids :: " + string);
            session.set("Seq_Ids", string);  
        }
}