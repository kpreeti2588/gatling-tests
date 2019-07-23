import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object CameraCategoryUpgradeRequest {

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString	
 
  val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\"}}";
  
  val getCameraCategoryData = http("cameraCategory")
    .post(Environment.VVAPI+"/v2/cameraCategory/relocation")
    //.post("/v2/cameraCategory/relocation") 
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString))
    .check(status.is(200))
}