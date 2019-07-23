import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.parsing.json._
import scala.util.control.Breaks._

object VVMemberEvents {

	val uuids = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString))
    

  	val getVVMemberEventsHttp = http("VV Member Events")                 
                    	  .get("/api/event-details?_format=json&time")
                    	  .header("TMPS-Correlation-Id", "${uuid}")
                        .check(status.is(200))
                        //.check(bodyString.saveAs("Event_Response"))
                        .check(jsonPath("$.15IOFULL.event_id").optional.saveAs("VVEvent_id"))
                        .check(jsonPath("$.15IOFULL.organization").optional.saveAs("VVorganization"))
                        .check(jsonPath("$.15IOFULL.plan_type").optional.saveAs("VVplan_type"))
                        .check(jsonPath("$.15IOFULL.event_description").optional.saveAs("VVevent_description"))
                        .check(jsonPath("$.15IOFULL.minor_cat").optional.saveAs("VVminor_cat"))
                        .check(jsonPath("$.15IOFULL.event_name").optional.saveAs("VVevent_name"))
                        .check(jsonPath("$.15IOFULL.major_cat").optional.saveAs("VVmajor_cat"))
                        .check(jsonPath("$.15IOFULL.total_events").optional.saveAs("VVtotal_events"))
                        .check(jsonPath("$.15IOFULL.multiple_seats_per_pid").optional.saveAs("VVmultiple_seats_per_pid"))
                        .check(jsonPath("$.15IOFULL.season_name").optional.saveAs("VVseason_name"))
                        .check(jsonPath("$.15IOFULL.events_in_plan[*].event_name").optional.saveAs("event_name"))
                        .check(jsonPath("$.15IOFULL.manifest_name").optional.saveAs("VVmanifest_name"))
                        .check(jsonPath("$.15IOFULL.archtics_venue_id").optional.saveAs("VVarchtics_venue_id"))
                        .check(jsonPath("$.15IOFULL.event_time").optional.saveAs("VVevent_time"))
                        .check(jsonPath("$.15IOFULL.venue_name").optional.saveAs("VVvenue_name"))
                        .check(jsonPath("$.15IOFULL.performer").optional.saveAs("VVperformer"))
                        .check(jsonPath("$.15IOFULL.event_category").optional.saveAs("VVevent_category"))
                        .check(jsonPath("$.15IOFULL.event_date").optional.saveAs("VVevent_date"))
                        .check(jsonPath("$.15IOFULL.fantm_event_name").optional.saveAs("VVfantm_event_name"))
                        .check(jsonPath("$.15IOFULL.event_name_inet").optional.saveAs("VVevent_name_inet"))
                        .check(jsonPath("$.15IOFULL.allow_singles").optional.saveAs("VVallow_singles"))
                       

      

                       
  	val getVVAllEvents = scenario("VV Member Events")
  		.feed(uuids)
		.exec(getVVMemberEventsHttp).pause(2)
}