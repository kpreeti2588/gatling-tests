import io.gatling.core.Predef._
import io.gatling.http.Predef._

object MemberList {
  	val getMembersHttp = http("Member List")
                        .get("/api/member/list?_format=json")
                        .check(status.is(200))
                        .check(jsonPath("$..member_id").optional.saveAs("member_id"))

  	val getMembers = scenario("Member List")
	 	.exec(getMembersHttp).pause(2)

	
}