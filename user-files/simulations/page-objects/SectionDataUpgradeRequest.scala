import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object SectionDataUpgradeRequest {

  var csvFeeder = csv(Environment.csv1).circular;
    if(Environment.runType == "unique"){
      csvFeeder = csv(Environment.csv1).queue;
    }

   val repeats : Int = Integer.getInteger("repeats", 1);

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString

 // val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
     //  "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\"}}";

 // val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
     //  "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\":\""+"${group}"+"\"}}";

   val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\"}}";

    //     val postBody = "{\"email\":\""+"${email}"+"\"}"

  println(userString);

  val getSectionDataOfGroup1 = http("sectionData1")
    .post(Environment.VVAPI+"/v2/sectionData/relocation")
    //.post("/v2/sectionData/relocation") 
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString))
    .check(status.is(200))

  

    val getSectionData = scenario("sectionData1").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(getSectionDataOfGroup1).pause(2)
    } 


}
