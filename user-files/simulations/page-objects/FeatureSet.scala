import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoadFeatureSet {
	val getFeatureSet = http("Content Feature Set")
                        .get("/ContentFeatureSet#")
                        .check(status.is(200))
                        
                        
	val getFeature = scenario("Content Feature Set")
     .exec(getFeatureSet).pause(2)
}