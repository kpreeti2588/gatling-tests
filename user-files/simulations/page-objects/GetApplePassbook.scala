import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetApplePassbook {
  
	val getPassbookImage = http("Apple Passbook")
            .get("${passbook_url}")
            .check(status.in(200,304))
            .check(bodyBytes.exists)
                        
	val getPassbook = scenario("Apple Passbook")
          	.exec(getPassbookImage).pause(2)
}