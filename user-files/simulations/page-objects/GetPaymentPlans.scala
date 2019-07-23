import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object PaymentPlans {
  	val getPaymentPlansHttp = http("Payment Plans")                 
                    	 	.get("/api/invoice/plans?_format=json")
                        	.check(status.is(200))
                        	.check(bodyString.saveAs("Payment_Plans"))

                        
  	val getPaymentPlans = scenario("Payment Plans")
		.exec(getPaymentPlansHttp).pause(2)
}