import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object TransferV2 {
  	val getTicketIdV2 = scenario("Get transfer ticket id")
  		.exec{session =>
      	   session.set("Event_id", "-1").set("TransferTicketId", "");
      	}
        .exec{session =>
          val evntId = session("Event_Ids").as[Vector[Int]];
          var n = evntId.length - 1;
          
          session.set("ind", n).set("even", "").set("allIds","");
        }
        .asLongAs(session => session("ind").as[Int] >= 0 && session("Event_id").as[String] == "-1") {

          exec { session =>
            var ind = session("ind").as[Int];
            val evntId = session("Event_Ids").as[Vector[Int]];
            session.set("current", evntId(ind));
            }
          
        .exec{session =>
            var even = session("even").as[String];
            even = even + "event_id[]=" + session("current").as[String] + "&";
             session.set("even", even); 
                
        }
 .exec{session =>
            var ind = session("ind").as[Int];
            ind = ind - 1;
            session.set("ind", ind); 

        }
      }

       .exec(http("Event Call V2 ").get("/api/v2/events?${even}_format=json").check(status.is(200))
          .check(bodyString.saveAs("Event_Response"))
          .check(jsonPath("$.events[*]").ofType[Map[String,Any]].findAll.saveAs("pList"))
       )
       
       .foreach("${pList}", "player") {
          doIf(session => session("TransferTicketId").as[String] == "") {
        exec{session => 
          val playerMap = session("player").as[Map[String,Any]]
          val eventId = playerMap("id")
          val sect =  playerMap("sections")
          val count = new java.util.concurrent.atomic.AtomicInteger(0)
          val asd = "" + sect;
           var allIds = "";
            
          session.set("currentEventId", eventId);  
        
           for ( x <- asd.split("},")) {
             val first = x.split(", id=")
             val second = first(1).split(",")
             val main = second(0)
             allIds = allIds + main + "," ;
             var coun = count.getAndIncrement
             session.set("sectionId"+eventId+coun,main).set(eventId+"count",coun);
             
            }
          val namesColl = allIds.split(",")
           
           session.set("allIds",namesColl);
            
            
        }
        .exec{session =>
              val count = 0
           session.set("countsss",count);
         
            }
        
        .repeat(session => session("allIds").as[Array[String]].size) {
           doIf(session => session("TransferTicketId").as[String] == "") {
            exec{session =>
              val count =  session("countsss").as[Int];
             val secId =  session("allIds").as[Array[String]];
              val playerMap = session("player").as[Map[String,Any]]
              val eventId = playerMap("id")
             session.set("currentSectionId",secId(count)).set("Event_id",eventId);
            }
            
          .exec(http("Section Call V2").get("/api/v2/member/events/${Event_id}/sections/${currentSectionId}?_format=json").check(status.is(200))
          .check(bodyString.saveAs("TicketResponse"))
          .check(jsonPath("$.rows[*].seats[?(@.actions[?(@.canTransfer == true)])].id").optional.saveAs("TransferTicketId")))
             
       
             .exec(session =>
            {
              println("ASDNASJDS"+session("TransferTicketId").as[String]);
                 session
            })
            
           .exec{session =>
              val count =session("countsss").as[Int] + 1;
               session.set("countsss",count);
            }        
       }
        }
       }
       }

          
}


