import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object CCQuery {
  	val getCCQueryHttp = http("CC Query")                 
                    	 	.get("/api/invoice/cc?_format=json")
                        	.check(status.is(200))
                        	.check(bodyString.saveAs("CC Query"))

                        
  	val getCCQuery = scenario("CC Query")
		.exec(getCCQueryHttp).pause(2)
}