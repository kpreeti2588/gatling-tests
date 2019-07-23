import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object TransferV2Multiple {
  val getTicketIdV2Multiple = scenario("Get transfer ticket id")
    .exec { session =>
      session.set("Event_id", "-1").set("TransferTicketId", "");
    }
    .exec { session =>
      val evntId = session("Event_Ids").as[Vector[Int]];
      var n = evntId.length - 1;

      session.set("ind", n).set("even", "").set("allIds", "");
    }
    .asLongAs(session => session("ind").as[Int] >= 0 && session("Event_id").as[String] == "-1") {

      exec { session =>
        var ind = session("ind").as[Int];
        val evntId = session("Event_Ids").as[Vector[Int]];
        session.set("current", evntId(ind));
      }

        .exec { session =>
          var even = session("even").as[String];
          even = even + "event_id[]=" + session("current").as[String] + "&";
          session.set("even", even);

        }
        .exec { session =>
          var ind = session("ind").as[Int];
          ind = ind - 1;
          session.set("ind", ind);

        }
    }

    .exec(http("Event Call V2 ").get("/api/v2/events?${even}_format=json").check(status.is(200))
      .check(bodyString.saveAs("Event_Response"))
      .check(jsonPath("$.events[*]").ofType[Map[String, Any]].findAll.saveAs("eventCall"))
       .check(jsonPath("$.isLarge").find.saveAs("isLarge"))
       .check(jsonPath("$.events[*].sections[*].rows[*].seats[?(@.actions[?(@.canTransfer == true)])].id").findAll.transformOption(extract => extract match {
                case None => Some(Nil)
                case someNEL => someNEL
              }).saveAs("allTicketIdss")))
              
      .doIf(session => session("isLarge").as[String] == "false") {
      
          doIf(session => session("allTicketIdss").as[List[String]].size != 0) {
              exec(session =>
      {
        session.set("Event_id", "0")
      })
              .exec { session =>
                val ticketIds = session("allTicketIdss").as[Vector[String]];
                var n = ticketIds.length - 1;
                session.set("tickInd", n);
              }
                .asLongAs(session => session("tickInd").as[Int] >= 0) {
                  exec { session =>
                    var ind = session("tickInd").as[Int];
                    val transferTickId = session("allTicketIdss").as[Vector[String]];
                    session.set("curr", transferTickId(ind));
                  }

                    .exec { session =>
                      var tickId = session("TransferTicketId").as[String];
                      tickId = tickId + "\"" + session("curr").as[String] + "\",";
                      session.set("TransferTicketId", tickId);

                    }

                    .exec { session =>
                      var ind = session("tickInd").as[Int];
                      ind = ind - 1;
                      session.set("tickInd", ind);

                    }
                }
            }
    }
      
    .doIf(session => session("isLarge").as[String] == "true") {
      
    foreach("${eventCall}", "eventCalls") {
       exec { session =>
        val eventTicketCounter = 0
        session.set("eventTicketCounter", eventTicketCounter);
      }
      .exec { session =>
        val eventCalls = session("eventCalls").as[Map[String, Any]]
        val eventId = eventCalls("id")
        val sect = eventCalls("sections")
        val count = new java.util.concurrent.atomic.AtomicInteger(0)
        val asd = "" + sect;
        var allIds = "";

        session.set("currentEventId", eventId);

        for (x <- asd.split("},")) {
          val first = x.split(", id=")
          val second = first(1).split(",")
          val main = second(0)
          allIds = allIds + main + ",";
          var coun = count.getAndIncrement
          session.set("sectionId" + eventId + coun, main).set(eventId + "count", coun);

        }
        val namesColl = allIds.split(",")

        session.set("allIds", namesColl);

      }
        .exec { session =>
          val count = 0
          session.set("countsss", count);

        }

        .repeat(session => session("allIds").as[Array[String]].size) {
          exec { session =>
            val count = session("countsss").as[Int];
            val secId = session("allIds").as[Array[String]];
            val eventCalls = session("eventCalls").as[Map[String, Any]]
            val eventId = eventCalls("id")
            session.set("currentSectionId", secId(count)).set("Event_id", eventId);
          }

            .exec(http("Section Call V2").get("/api/v2/member/events/${Event_id}/sections/${currentSectionId}?_format=json").check(status.is(200))
              .check(bodyString.saveAs("TicketResponse"))
              .check(jsonPath("$.rows[*].seats[?(@.actions[?(@.canTransfer == true)])].id").findAll.transformOption(extract => extract match {
                case None => Some(Nil)
                case someNEL => someNEL
              }).saveAs("currentTicket")))

            .doIf(session => session("currentTicket").as[List[String]].size != 0) {
              exec { session =>
                val ticketIds = session("currentTicket").as[Vector[String]];
                var n = ticketIds.length - 1;
                session.set("tickInd", n);
              }
                .asLongAs(session => session("tickInd").as[Int] >= 0 && session("eventTicketCounter").as[Int] < 7) {
                  exec { session =>
                    var ind = session("tickInd").as[Int];
                    val transferTickId = session("currentTicket").as[Vector[String]];
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

            .exec { session =>
              val count = session("countsss").as[Int] + 1;
              session.set("countsss", count);
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
        session.set("TransferTicketId",transferTicket3)
      })

      .exec(session =>
      {
        println("asdas "+session("TransferTicketId").as[String]);
        session
      })
      
     }
     {
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


