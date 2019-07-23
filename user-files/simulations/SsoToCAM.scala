import util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SsoToCAM extends Simulation {

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

  println("Number of threads :: " + users);
  println("Rampup period :: " + rps);
  println("Duration :: " + dur);
  println("Blocks :: " + blocks);
  println("Delay :: " + delay);
  println("Ramped Users :: " + rampedUsers);
  println("Throttle value :: " + throttle);
   
  val httpConf = http.baseURL(Environment.baseURL).headers(Headers.commonHeader).disableWarmUp
                  
  var myScenario = scenario("SSO To CAM")
    .during(dur seconds) {
      exitBlockOnFail(
         exec(flushSessionCookies)
         .exec(flushCookieJar)
         .exec(flushHttpCache)
         .exec(UserLogin.login)
         .exec(SSOToCAM.cam_sso)
         .pause(1000 milliseconds, 5000 milliseconds)
      )
   }

   if(dur == 0) {
      myScenario = scenario("SSO To CAM")
      .exitBlockOnFail(
         exec(flushSessionCookies)
         .exec(flushCookieJar)
         .exec(flushHttpCache)
         .exec(UserLogin.login)
         .exec(SSOToCAM.cam_sso)
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
      )

      setUp(run)
         .throttle(jumpToRps(throttle), holdFor(dur seconds))
         .protocols(httpConf)
         .assertions(
            global.responseTime.max.lessThan(Environment.maxResponseTime.toInt)
      )   
   }
}