import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object TransferV2MultipleNew {

  val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))

  val getTicketIdV2MultipleNew = scenario("Get transfer ticket id")

    .exec { session =>
      session.set("Event_id", "-1").set("TransferTicketId", "");
    }
    .exec { session =>
      val evntId = session("Event_Ids").as[Vector[Int]];
      var n = evntId.length - 1;

      session.set("ind", n).set("even", "").set("allIds", "");
    }
    .asLongAs(session => session("ind").as[Int] >=0) {

      exec { session =>
        val eventTicketCounter = 0
        session.set("eventTicketCounter", eventTicketCounter);
      }

        .exec { session =>
          var ind = session("ind").as[Int];
          val evntId = session("Event_Ids").as[Vector[Int]];
          session.set("current", evntId(ind));
        }

        .feed(uuids)
        .exec(http("Event Call V2 ").get("/api/v2/events/${current}?_format=json")
          .header("TMPS-Correlation-Id", "${uuid}")
          .check(status.is(200))
          .check(bodyString.saveAs("Event_Response"))
          //.check(jsonPath("$.events[*]").ofType[Map[String, Any]].findAll.saveAs("eventCall"))
          //.check(jsonPath("$.isLarge").find.saveAs("isLarge"))
          .check(jsonPath("$.sections[*].rows[*].seats[?(@.actions[?(@.canTransfer == true)])].id").findAll.transformOption(extract => extract match {
            case None => Some(Nil)
            case someNEL => someNEL
          }).saveAs("allTicketIdss")))

        .exec { session =>
          var ind = session("ind").as[Int];
          ind = ind - 1;
          session.set("ind", ind);

        }

        .doIf(session => session("allTicketIdss").as[List[String]].size != 0) {
          exec(session =>
            {
              session.set("Event_id", "0")
            })
            .exec { session =>
              val ticketIds = session("allTicketIdss").as[Vector[String]];
              var n = ticketIds.length - 1;
              session.set("tickInd", n);
            }
            .asLongAs(session => session("tickInd").as[Int] >= 0 && session("eventTicketCounter").as[Int] < 2) {
              exec { session =>
                var ind = session("tickInd").as[Int];
                val transferTickId = session("allTicketIdss").as[Vector[String]];
                session.set("curr", transferTickId(ind));
              }

                .exec { session =>
                  var tickId = session("TransferTicketId").as[String];
                  var eventTicketCounter = session("eventTicketCounter").as[Int];
                  eventTicketCounter = eventTicketCounter + 1;
                  tickId = tickId + "\"" + session("curr").as[String] + "\",";
                  session.set("TransferTicketId", tickId).set("eventTicketCounter", eventTicketCounter);
                  //session.set("eventTicketCounter", eventTicketCounter);

                }

                .exec { session =>
                  var ind = session("tickInd").as[Int];
                  ind = ind - 1;
                  session.set("tickInd", ind);
                }
            }
        }
    }
    .doIfOrElse(session => session("TransferTicketId").as[String] != "") {
      exec(session =>
        {
          val transferTicket = session("TransferTicketId").as[String];
          val transferTicket1 = transferTicket.dropRight(1)
          val transferTicket2 = transferTicket1.dropRight(1)
          val transferTicket3 = transferTicket2.substring(1)
          session.set("TransferTicketId", transferTicket3)
        })

        .exec(session =>
          {
            println("asdas " + session("TransferTicketId").as[String]);
            session
          })

    } {
      exec(session =>
        {
          session.set("Event_id", "-1")
        })

        .exec(session =>
          {
            println("No transfer eligible tickets");
            session
          })
    }

}


