import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object Render {
  	val getTicketIds = scenario("Get render ticket ids")
      .exec{session =>
         session.set("Event_id", "-1").set("RenderTicketId", "");;
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
         .check(jsonPath("$..sections[*].rows[*].tickets[?(@.can_render_barcode==true)].ticket_id").optional.saveAs("RenderTicketId"))
         .check(jsonPath("$..sections[*].rows[*].tickets[?(@.can_render_barcode==true)].ticket_id").findAll.optional.saveAs("RenderTicketIds")))
         .doIf(session => session("RenderTicketId").as[String] != "") {
            exec{session =>
               var event_id = session("current_event_id").as[String];
               session.set("Event_id", event_id);
            }
         }
      }
      .exec(session => {
         var event_id = session("Event_id").as[String];
         println("EVENT_ID :: " + event_id);
         session
      })
}