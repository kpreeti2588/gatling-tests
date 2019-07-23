import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object SectionAvailabilityUpgradeRequest {

  var csvFeeder = csv(Environment.csv1).circular;
    if(Environment.runType == "unique"){
      csvFeeder = csv(Environment.csv1).queue;
    }

   val repeats : Int = Integer.getInteger("repeats", 1);

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString  

  //val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
   //    "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"memberId\":\"" + Environment.memberId + "\"}}";
  
  val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"memberId\": \"" + Environment. memberId + "\"}}";
  
  println(userString)
  val getSectionAvailabilityDataOfGroup1 = http("sectionAvailability1")
    .post(Environment.VVAPI+"/v2/sectionAvailability/relocation")
    //.post("/v2/sectionAvailability/relocation") 
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .header("sectionname",Environment.sectionname)
    .body(StringBody(userString))
    .check(status.is(200))
  

    val getSectionAvailabilityData = scenario("sectionAvailability1").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(getSectionAvailabilityDataOfGroup1).pause(2)
    } 
}
