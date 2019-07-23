import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object CameraCategoryPostRequest {

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString	

  
  val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";

  
  val getCameraCategoryData = http("cameraCategory")
    .post(Environment.VVAPI+"/v2/cameraCategory/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString))
    .check(status.is(200))
}