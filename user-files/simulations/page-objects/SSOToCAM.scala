import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SSOToCAM {
	val sso = http("SSO To CAM")
      .get("/classic-amgr?redirect_url=account/settings")
      .check(status.in(302,200))
      .check(css("#currentAccount:contains(\"${NickName}\")").exists)
    	//.check(css("#currentAccount").is("Account "+"${NickName}"))
                        
	val cam_sso = scenario("SSO To CAM")
     	.exec(sso).pause(2)
}