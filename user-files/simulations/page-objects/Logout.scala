import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Logout {
  
	val  userLogout = http("Logout")
                        .get("/user/logout")
                        
	val logout = scenario("Logout")
     .exec(userLogout).pause(2)
}