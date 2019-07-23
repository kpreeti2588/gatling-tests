import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadVVPage {

	val getVVPageResources = http("Load VV Page")
                       // .get("/virtual-venue/15IOFULL#/map/1")
                       .get("/virtual-venue/15IOFULL#/map/1")
                      //  .get("/virtual-venue#/map/1")

                       // https://stg1-am.ticketmaster.com/loadtest/virtual-venue#/map/1

                        //.get("https://stg1-am.ticketmaster.com/vvqa/virtual-venue/15IOFULL#/map/1")

                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","")
                        .resources(
                     //   http("stadium-map.chunk.js").get("https://vv2-preprodpci.ppub-tmaws.io/v201/js/vendors~camera-detail-view~compare~overview~seat-stadium-map.chunk.js")
                       // http("overview~seat-stadium-map.chunk.js").get("https://vv2-preprodpci.ppub-tmaws.io/v201/js/vendors~compare~overview~seat-stadium-map.chunk.js"),
                       //  http("seat-stadium-map.chunk.js").get("https://vv2-preprodpci.ppub-tmaws.io/v201/js/vendors~overview~seat-stadium-map.chunk.js"),
                       //  http("overview.chunk.js").get("https://vv2-preprodpci.ppub-tmaws.io/v201/js/overview.chunk.js")
                        ).check(status.is(200))
                        
	val getVVPage = scenario("Load VV Page")
     .exec(getVVPageResources).pause(2)
}