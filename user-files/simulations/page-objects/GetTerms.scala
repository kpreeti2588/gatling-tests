import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetTerms {
  
  val getTermsHttp = http("Get Terms")
                    .get("/api/text/terms?_format=json")
                    .check(status.is(200))
                    .check(jsonPath("$..version").optional.saveAs("version"))
                    .check(jsonPath("$..show_terms").optional.saveAs("show_terms"))

  val getTerms = scenario("Get Terms") 
                .exec(getTermsHttp).pause(2)
                .doIfEquals("${show_terms}", "true")	{
                  exec(GetToken.getToken)
                  .exec(AcceptTerms.acceptTerms)
                }
    }