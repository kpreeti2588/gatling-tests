import io.gatling.core.Predef._
import io.gatling.http.Predef._
import util._


object InitializeUpdateRequest {

  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString
  //val xtransid = Iterator.continually(Map("xtransid" -> (Random.alphanumeric.take(60).mkString)))

   val randoms = Iterator.continually(
      Map("random" -> Random.nextInt(Integer.MAX_VALUE))
    )

 // val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ",\"dataState\":" + Environment.dataState + ","+
   //    "\"srcSysName\":\"" + Environment.srcSysName + "\",\"targetEvent\":\"" + Environment.targetEvent + "\",\"memberId\":\"${member_id}\"}}";

   val userString = "{\"command\":{\"venueId\":" + Environment.venueId + ","+
      "\"srcSysName\":\"" + Environment.srcSysName + "\",\"namBaseUrl\":\"" + Environment.namBaseUrl + "\"}}"; 

      

  //val xtransid = randomString(60)
  
  //val xtransid = StringBody("""${xtransid}""")
  println(userString)

  val getInitializeData = http("Initialize")
    .post(Environment.VVAPI+"/v2/initialize/relocation")
    //.post("/v2/initialize/relocation")
    .header("content-type", "application/json")
    .header("tmcorrelationid", randomString(36))
    .header("xtransid", "${random}")
    .header("datatoken", Environment.datatoken)
    .body(StringBody(userString))
    .check(status.is(200))


    val getInitializeXtransId = scenario("Getting xtransid")
        .feed(randoms)
        .exec(getInitializeData).pause(2)
      //   .exec{session =>
      //    session.set("xtransid", "${random}")
      // }

}