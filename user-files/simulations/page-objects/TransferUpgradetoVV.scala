import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TransferUpgradetoVV {

    //   def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString
    //   //val xtransid = Iterator.continually(Map("xtransid" -> (Random.alphanumeric.take(60).mkString)))

    //   val randoms = Iterator.continually(
    //   Map("random" -> Random.nextInt(Integer.MAX_VALUE))
    // )
val user = "{\"is_preview\":\"false\",\"target_event\":{\"name\":\"" + Environment.targetEvent + "\",\"title\":\"2019 Season Tickets\",\"description\":\"This is a new event description I am adding\",\"imgUrl\":\"${imgUrl}\"},\"source_seats\":[{\"row_name\":\"${rowname}\",\"last_invoice_id\":\"${lastinvoiceid}\",\"seat_label\":\"${seat_label}\",\"seat_num\":\"${seat_num}\",\"block_purchase_price\":\"${block_purchase_price}\",\"section_label\":\"${section_label}\",\"section_name\":\"${section_name}\",\"seat_increment\":\"${seat_increment}\",\"event_name\":\"${event_name}\",\"eligible_events\":[{\"eligible_event\":\"${eligible_event}\",\"imgUrl\":\"${imgUrl}\"}],\"block_full_price\":\"${block_full_price}\",\"ticket_type_text\":\"${ticket_type_text}\",\"full_price\":\"${full_price}\",\"req_don_ind\":\"${req_don_ind}\",\"paid_amount\":\"${paid_amount}\",\"onsale_date_time\":\"${onsale_date_time}\",\"add_date_time\":\"${add_date_time}\",\"ga_indicator\":\"${ga_indicator}\",\"ticket_type_code\":\"${ticket_type_code}\",\"last_seat\":\"${last_seat}\",\"offsale_date_time\":\"${offsale_date_time}\",\"price_code\":\"${price_code}\",\"purchase_price\":\"${purchase_price}\",\"num_seats\":\"${num_seats}\",\"upgrade_status\":\"${upgrade_status}\",\"row_label\":\"${row_label}\",\"relocMode\":\"${relocMode}\",\"isPreview\":\"${isPreview}\",\"imgUrl\":\"${vvimgUrl}\",\"event_details\":{\"event_id\":\"${VVEvent_id}\",\"organization\":\"${VVorganization}\",\"plan_type\":\"${VVplan_type}\",\"event_description\":\"${VVevent_description}\",\"minor_cat\":\"${VVminor_cat}\",\"event_name\":\"${VVevent_name}\",\"major_cat\":\"${VVmajor_cat}\",\"total_events\":\"${VVtotal_events}\",\"multiple_seats_per_pid\":\"${VVmultiple_seats_per_pid}\",\"season_name\":\"${VVseason_name}\",\"events_in_plan\":[{\"event_name\":\"${event_name}\"}],\"manifest_name\":\"${VVmanifest_name}\",\"archtics_venue_id\":\"${VVarchtics_venue_id}\",\"event_time\":\"${VVevent_time}\",\"venue_name\":\"${VVvenue_name}\",\"performer\":\"${VVperformer}\",\"event_category\":\"${VVevent_category}\",\"event_date\":\"${VVevent_date}\",\"fantm_event_name\":\"${VVfantm_event_name}\",\"event_name_inet\":\"${VVevent_name_inet}\",\"allow_singles\":\"${VVallow_singles}\"}}]}";
 

  println(user)    

	val postUpgradeData = http("Transfer Upgrade to VV")
                        .post("/api/v1/members/upgrade-list?_format=json")
                        //.header("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
                        .header("Referer","")
                        .header("content-type", "application/json")
                        .header("x-csrf-token", "${token}")
                       //  .header("tmcorrelationid", randomString(36))
                       //  .header("xtransid", "${random}")
                        .body(StringBody(user))
  											.check(status.is(200))
                    //    .check(status.in(200,304))

                    //    .check(css("#mainNav").exists)
                    //    .check(regex("<title>Game Day</title>"""))


	val TransfertoVV = scenario("Transfer Upgrade to VV")
     .exec(postUpgradeData).pause(15)
  	


}
