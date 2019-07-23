import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object InvoiceList {    
   val getInvoiceList = scenario("Invoice List")
      .exec(http("Invoice List")
      .get("/api/invoice/list?_format=json")
      .check(status.is(200))
      .check(jsonPath("$.data[*].invoice_ids").findAll.saveAs("Invoice_Ids"))
      .check(jsonPath("$.data[*].balances").findAll.saveAs("Balances")))
     // .check(jsonPath("$.data[?(@.payment_plan_id)].balances").findAll.saveAs("Balances"))
    //  .check(jsonPath("$.data[*].payment_plan_id").findAll.saveAs("Plan_Id"))
     // .check(jsonPath("$.data[?(@.payment_plan_id)].invoice_ids").findAll.saveAs("Invoice_Ids")))
      .exec{session =>
         session.set("PlanInvoice_Id", "-1");
      }
      .exec{session =>
         val invoice_ids = session("Invoice_Ids").as[Vector[Int]];
         var i = invoice_ids.length - 1;
         session.set("index", i);
      }
      .asLongAs(session => session("index").as[Int] >= 0 && session("PlanInvoice_Id").as[String] == "-1" ) {
         exec{session =>
            var index = session("index").as[Int];
            val invoice_idss = session("Invoice_Ids").as[Vector[Int]];
            session.set("current_invoice_id", invoice_idss(index));
         }
         .exec{session =>
            var index = session("index").as[Int];
            val balances = session("Balances").as[Vector[Int]];
            println("balance ::" + balances(index));
            session.set("current_invoice_balance", balances(index));
            
         }   
         .doIf(session => session("current_invoice_balance").as[String].toFloat  > 0) {
            exec{session =>
               var invoice_id = session("current_invoice_id").as[String];
               session.set("PlanInvoice_Id", invoice_id);
            }
         }
         .exec{session =>
            var index = session("index").as[Int];
            index = index - 1;
            session.set("index", index);
         }
}
      .exec(session => {
         var invoice_id = session("current_invoice_id").as[String];
            println("INVOICE_ID :: " + invoice_id);
            session.set("invoice_id",invoice_id)
         })
}