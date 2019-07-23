import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadLayoutC {
	val getPageLayoutC = http("Layout C")
                        .get("/Layout2#")
                        .resources(

                        http("Slider.js").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/Slider/Slider.js"),
                        http("FeatureBox").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/FeatureBox/FeatureBox.js"),
                        http("ImageGallery").get("https://amgr-tm-qa.s3.amazonaws.com/content-manager/ImageGallery/ImageGallery.js")
                     
                        )
                        
                        
	val getLayoutC = scenario("Layout C")
     .exec(getPageLayoutC).pause(2)
}