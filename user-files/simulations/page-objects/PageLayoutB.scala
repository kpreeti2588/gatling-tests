import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadPageLayoutB {
	val getPageLayoutB = http("Sports Layout B")
                        .get("/SportsLayout2#")
                        .resources(
                        http("Slider.js").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/Slider/Slider.js"),
                        http("FeatureBox").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/FeatureBox/FeatureBox.js"),
                        http("ImageGallery").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/ImageGallery/ImageGallery.js")
                        )     
                        
	val getLayoutB = scenario("Sports Layout B")
     .exec(getPageLayoutB).pause(2)
}