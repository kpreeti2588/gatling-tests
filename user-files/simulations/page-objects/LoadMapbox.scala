import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadMapboxPage {
	val getVVPageResources = http("Load Mapbox Page")
                        .get("/virtual-venue/15IOFULL#/map/1/318")
                        //.get("https://stg1-am.ticketmaster.com/vvqa/virtual-venue/15IOFULL#/map/1/305")

                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","")
                         .resources(
                          http("map-seat-stadium-map.chunk.js").get("https://vv2-preprodpci.ppub-tmaws.io/v201/js/seat-stadium-map.chunk.js")
                        ).check(status.is(200))
                        
	val getMapboxPage = scenario("Load Home Page")
     .exec(getVVPageResources).pause(2)
}