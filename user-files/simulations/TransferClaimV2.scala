import util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TransferClaimV2 extends Simulation {

   def divide(a: Int, b: Int): Int = {
      a / b
   }

   var users = Integer.getInteger("threads",  100);
   val rps = java.lang.Long.getLong("rampup", 30L);
   val dur = java.lang.Long.getLong("duration", 0L);
   val blocks = Integer.getInteger("blocks",  1);
   val delay = java.lang.Long.getLong("delay", 15L);
   val throttle = Integer.getInteger("throttle",  10);

  var rampedUsers = divide(users, blocks);

  var csvFeeder = csv(Environment.csv).circular;
  if(Environment.runType == "unique") {
    csvFeeder = csv(Environment.csv).queue;
    if(users > csvFeeder.records.length) {
      users = csvFeeder.records.length;
    }
  }

  println("Number of threads :: " + users);
  println("Rampup period :: " + rps);
  println("Duration :: " + dur);
  println("Blocks :: " + blocks);
  println("Delay :: " + delay);
  println("Ramped Users :: " + rampedUsers);
  println("Throttle value :: " + throttle);
  
  val httpConf = http.baseURL(Environment.baseURL).headers(Headers.commonHeader).disableWarmUp
  //.inferHtmlResources(BlackList("""^https://.*\.cloudfront\..*""")/*, WhiteList("""^http://.*\.io-media.com/.*""")*/)

  var myScenario = scenario("Transfer Claim")
    .during(dur seconds) {
      exitBlockOnFail(
         exec(flushSessionCookies)
         .exec(flushCookieJar)
         .exec(flushHttpCache)
         .exec(LoadHomePage.getHomePage)
         .exec(UserLogin.login)
         .exec(LoadDashboard.getDashboard)
         .exec(MemberList.getMembers)
         //.exec(EventsSummary.getEventSummary)
         //.exec(EventList.getEventList)
         .exec(LoadMyEvent.getEvent)
         .exec(MemberEvents.getAllEvents)
         .exec(TransferV2MultipleNew.getTicketIdV2MultipleNew)
        // .exec(TransferAndClaim.transferClaim)
         .exec(BulkTransfer.bulkClaim)
         .exec(Logout.logout)
         .pause(1000 milliseconds, 5000 milliseconds)
      )
   }

   if(dur == 0) {
      myScenario = scenario("Transfer Claim")
         .exitBlockOnFail(
            exec(flushSessionCookies)
            .exec(flushCookieJar)
            .exec(flushHttpCache)
            .exec(LoadHomePage.getHomePage)
            .exec(UserLogin.login)
            .exec(LoadDashboard.getDashboard)
            .exec(MemberList.getMembers)
            //.exec(EventsSummary.getEventSummary)
            //.exec(EventList.getEventList)
            .exec(LoadMyEvent.getEvent)
            .exec(MemberEvents.getAllEvents)
            .exec(TransferV2MultipleNew.getTicketIdV2MultipleNew)
            //.exec(TransferAndClaim.transferClaim)
            .exec(BulkTransfer.bulkClaim)
            .exec(Logout.logout)
            .pause(1000 milliseconds, 5000 milliseconds)
      )

      val run = myScenario.inject(
         rampUsers(users) over (rps seconds)
         //splitUsers(users) into (rampUsers(rampedUsers) over (rps seconds)) separatedBy (delay seconds)
      )

      setUp(run)
         //.throttle(jumpToRps(throttle), holdFor(dur seconds))
         .protocols(httpConf)
         .assertions(
            global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
      )
   } else {
      val run = myScenario.inject(
         //rampUsers(users) over (rps seconds)
         splitUsers(users) into (rampUsers(rampedUsers) over (rps seconds)) separatedBy (delay seconds)
         //atOnceUsers(users)
      )

      setUp(run)
         .throttle(jumpToRps(throttle), holdFor(dur seconds))
         .protocols(httpConf)
         .assertions(
            global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
      )   
   }
}