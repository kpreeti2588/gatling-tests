import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadLiveEventLayoutB{
	val getEventLayoutB = http("Live Event Layout B")
                        .get("/LiveEventsLayout2#")
                        .resources(
                        http("Slider.js").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/Slider/Slider.js"),
                        http("FeatureBox").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/FeatureBox/FeatureBox.js"),
                        http("ImageGallery").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/ImageGallery/ImageGallery.js")
                        ).check(status.is(200))
                        
                        
	val getLiveEventLayoutB = scenario("Live Event Layout B")
     .exec(getEventLayoutB).pause(2)
}