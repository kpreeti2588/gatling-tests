import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadImageGallery {
	val getImageGalleryResources = http("Image Gallery")
                        .get("/ContentImageGallery#")
                        .check(status.is(200))
                        
                        
	val getImageGallery = scenario("Load Image Gallery")
     .exec(getImageGalleryResources).pause(2)
}