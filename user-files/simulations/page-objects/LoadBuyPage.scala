import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadBuyPage {
	val getBuyPage = http("Load Buy Page")
			//.get("/tickets#/${Event_id}")
            .get("/buy")
            .header("Referer","")       
           // .resources(
           //              http("tawmrikzwqrbcsoo").get("https://stg1-am.ticketmaster.com/tawmrikzwqrbcsoo.js"),
           //             http("Maintenance").get("https://am-stg-common-content.ppub-tmaws.io/maintenence/maintenence.js"))
                        .check(status.in(200, 304))

                        
	val getBuy = scenario("Load Buy Page")
	     	.exec(getBuyPage).pause(2)
}