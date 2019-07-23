import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LambdaUrl {
	val getLambdaHttp = http("Get Lambda")
	                    .get("/amgr-static-api/api/v1/members/unitas.293712.10019/inventory/summary")
                        .check(status.is(200))
                        .check(bodyString.saveAs("token"))

  	val getLambda = scenario("Get Lambda").exec(getLambdaHttp).pause(2)
}