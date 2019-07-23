import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadLiveEventLayoutA {

	val getEventLayoutA = http("Live Event Layout A")
                        .get("/LiveEventLayout1#")
                        .resources(
                        http("Slider.js").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/Slider/Slider.js"),
                        http("FeatureBox").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/FeatureBox/FeatureBox.js"),
                        http("ImageGallery").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/ImageGallery/ImageGallery.js")
                        ).check(status.is(200))
                        
	val getLiveEventLayoutA = scenario("Live Event A")
     .exec(getEventLayoutA).pause(2)
}