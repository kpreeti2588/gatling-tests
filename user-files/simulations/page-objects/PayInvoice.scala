import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random

object PayInvoice {
   val userString = "{\"acct_id\":\"${member_id}\",\"invoice_conf_id\":[${Seq_Ids}],\"credit_card\":[{\"amount\":\"0.01\",\"cc_num\":\"4111111111111111\",\"cin\":\"111\",\"first_name\":\"TestFirst\",\"last_name\":\"TestLast\",\"Avs_address\":\"TestStreet\",\"Avs_postal_code\":\"12345\",\"pmt_type\":\"VI\",\"exp_date\":\"1225\",\"display_on_inet\":\"N\"}],\"payment_plan_id\":\"${plan_id}\"}"


   val paymenthttp = http("Invoice Payment")
      .post("/api/invoice/${invoice_id}/payment?_format=json")
      .header("content-type", "application/json")
      .header("x-csrf-token", "${token}")
      .body(StringBody(userString))
      .check(status.is(200))
      .check(regex("Thank you for your payment!").exists)

   val payment = scenario("Invoice Payment")
		.exec(GetToken.getToken)
        .exec(paymenthttp).pause(2)
}