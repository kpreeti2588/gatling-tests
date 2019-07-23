import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._

object SectionAvailabilityPostRequest {

  var csvFeeder = csv(Environment.csv1).circular;
    if(Environment.runType == "unique"){
      csvFeeder = csv(Environment.csv1).queue;
    }

   val repeats : Int = Integer.getInteger("repeats", 1);

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString  


  //val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"memberId\": \"" + Environment. memberId + "\"}}";
 
// val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"memberId\":\"${member_id}\"}}";
  
 //val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"memberId\":\"${member_id}\"}}";

  //Anonymous users
  val userString ="{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\"}}";
  val getSectionAvailabilityDataOfGroup1 = http("sectionAvailability1")
    .post(Environment.VVAPI+"/v2/sectionAvailability/buy")
    //.post("/v2/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .header("sectionname",Environment.sectionname)
    .body(StringBody(userString))
    .check(status.is(200))

  //val userString2 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
   //     "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName2 + "\",\"memberId\":\"" + Environment.memberId + "\"}}";
 val userString2 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
      "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName2 + "\"}}";

  val getSectionAvailabilityDataOfGroup2 = http("sectionAvailability2")
    .post(Environment.VVAPI+"/v1/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString2))
    .check(status.is(200))


  //val userString3 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
   //    "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName3 + "\",\"memberId\":\"" + Environment.memberId + "\"}}";
   
   val userString3 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName3 + "\"}}";

  val getSectionAvailabilityDataOfGroup3 = http("sectionAvailability3")
    .post(Environment.VVAPI+"/v1/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString3))
    .check(status.is(200))
  
  //val userString4 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
  //     "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\",\"promoCode\":\"" + Environment.promoCode + "\",\"memberId\":\"" + Environment.memberId + "\"}}";
 
 val userString4 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName1 + "\"}}";

  val getSectionDataUsingPromocodeOfGroup1 = http("sectionAvailabilityWithPromocodeOfGroup1")
    .post(Environment.VVAPI+"/v1/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString4))
    .check(status.is(200))
  
 // val userString5 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
   //    "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName2 + "\",\"promoCode\":\"" + Environment.promoCode + "\",\"memberId\":\"" + Environment.memberId + "\"}}";

 val userString5 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName2 + "\"}}";

  val getSectionDataUsingPromocodeOfGroup2 = http("sectionAvailabilityWithPromocodeOfGroup2")
    .post(Environment.VVAPI+"/v1/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString5))
    .check(status.is(200))

  
 //val userString6 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
   //    "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName3 + "\",\"promoCode\":\"" + Environment.promoCode + "\",\"memberId\":\"" + Environment.memberId + "\"}}";
     val userString6 = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"language\": \""+Environment.language+"\",\"dataState\":" + Environment.dataState + ","+
       "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"groupName\": \"" + Environment.groupName3 + "\"}}";

  val getSectionDataUsingPromocodeOfGroup3 = http("sectionAvailabilityWithPromocodeOfGroup3")
    .post(Environment.VVAPI+"/v1/sectionAvailability/buy")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .body(StringBody(userString6))
    .check(status.is(200))

    val getSectionAvailabilityData = scenario("sectionAvailability1").repeat(repeats) {
       exec(feed(csvFeeder))
      .exec(getSectionAvailabilityDataOfGroup1).pause(2)
    } 
}
