import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadHomePagePublic {

	val getHomePagePublic = http("Home Page Public")
                        .get("/")
                        .resources(
                        http("Slider.js").get("https://stg-common-widgets.ppub-tmaws.io/1.0.23/Slider/Slider.js"),
                        http("FeatureBox").get("https://stg-common-widgets.ppub-tmaws.io/1.0.23/FeatureBox/FeatureBox.js"),
                        http("ImageGallery").get("https://stg-common-widgets.ppub-tmaws.io/1.0.23/ImageGallery/ImageGallery.js")
                        ).check(status.is(200))
                        
	val getHomePage = scenario("Home Page Public")
     .exec(getHomePagePublic).pause(2)
}