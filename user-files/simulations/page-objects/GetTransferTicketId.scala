import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object Transfer {
  	val getTicketId = scenario("Get transfer ticket id")
  		.exec{session =>
      	   session.set("Event_id", "-1").set("TransferTicketId", "");
           //session.set("TransferTicketId", "");
      	}
      	.exec{session =>
	 		val event_ids = session("Event_Ids").as[Vector[Int]];
         	var i = event_ids.length - 1;
         	session.set("index", i);
      	}
      	.asLongAs(session => session("index").as[Int] >= 0 && session("Event_id").as[String] == "-1") {
     		exec{session =>
            	var index = session("index").as[Int];
            	val event_ids = session("Event_Ids").as[Vector[Int]];
            	session.set("current_event_id", event_ids(index));
         	}
         	.exec{session =>
            	var index = session("index").as[Int];
            	index = index - 1;
            	session.set("index", index);
         	}
         	.exec(http("Event Call").get("/api/user-ticket/${current_event_id}?_format=json").check(status.is(200))
         	.check(bodyString.saveAs("Call_Response"))
         // .checkIf(jsonPath("$..sections[*].rows[*].tickets[?(@.can_transfer==true)].ticket_id")).exists.(check(jsonPath("$..sections[*].rows[*].tickets[?(@.can_transfer==true)].ticket_id").optional.saveAs("TransferTicketId"))))
         	.check(jsonPath("$..sections[*].rows[*].tickets[?(@.can_transfer==true)].ticket_id").optional.saveAs("TransferTicketId")))
         	.doIf(session => session("TransferTicketId").as[String] != "") {
            	exec{session =>
               		var event_id = session("current_event_id").as[String];
               		session.set("Event_id", event_id);
            	}
         	}
      	}
      	.exec(session => {
    	 	var event_id = session("Event_id").as[String];
         	println("EVENT_ID :: " + event_id );
         	session
      	})
}