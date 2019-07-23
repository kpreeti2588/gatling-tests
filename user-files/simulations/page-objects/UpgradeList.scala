import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UpgradeList {



  	val getUpgrade = http("Upgrade List")
                        .get("/api/v1/members/upgrade-list?_format=json")
                        .check(status.is(200))
                        .check(jsonPath("$.seats[*].row_name").optional.saveAs("rowname"))
                        .check(jsonPath("$.seats[*].last_invoice_id").optional.saveAs("lastinvoiceid"))
                        .check(jsonPath("$.seats[*].seat_label").optional.saveAs("seat_label"))
                        .check(jsonPath("$.seats[*].seat_num").optional.saveAs("seat_num"))
                        .check(jsonPath("$.seats[*].block_purchase_price").optional.saveAs("block_purchase_price"))
                        .check(jsonPath("$.seats[*].section_label").optional.saveAs("section_label"))
                        .check(jsonPath("$.seats[*].section_name").optional.saveAs("section_name"))
                        .check(jsonPath("$.seats[*].seat_increment").optional.saveAs("seat_increment"))
                        .check(jsonPath("$.seats[*].event_name").optional.saveAs("event_name"))
                        .check(jsonPath("$.seats[*].eligible_events[*].eligible_event").optional.saveAs("eligible_event"))
                        .check(jsonPath("$.seats[*].eligible_events[*].imgUrl").optional.saveAs("imgUrl"))
                        .check(jsonPath("$.seats[*].block_full_price").optional.saveAs("block_full_price"))
                        .check(jsonPath("$.seats[*].ticket_type_text").optional.saveAs("ticket_type_text"))
                        .check(jsonPath("$.seats[*].full_price").optional.saveAs("full_price"))
                        .check(jsonPath("$.seats[*].req_don_ind").optional.saveAs("req_don_ind"))
                        .check(jsonPath("$.seats[*].paid_amount").optional.saveAs("paid_amount"))
                        .check(jsonPath("$.seats[*].onsale_date_time").optional.saveAs("onsale_date_time"))
                        .check(jsonPath("$.seats[*].add_date_time").optional.saveAs("add_date_time"))
                        .check(jsonPath("$.seats[*].ga_indicator").optional.saveAs("ga_indicator"))
                        .check(jsonPath("$.seats[*].ticket_type_code").optional.saveAs("ticket_type_code"))
                        .check(jsonPath("$.seats[*].last_seat").optional.saveAs("last_seat"))
                        .check(jsonPath("$.seats[*].offsale_date_time").optional.saveAs("offsale_date_time"))
                        .check(jsonPath("$.seats[*].price_code").optional.saveAs("price_code"))
                        .check(jsonPath("$.seats[*].purchase_price").optional.saveAs("purchase_price"))
                        .check(jsonPath("$.seats[*].num_seats").optional.saveAs("num_seats"))
                        .check(jsonPath("$.seats[*].upgrade_status").optional.saveAs("upgrade_status"))
                        .check(jsonPath("$.seats[*].row_label").optional.saveAs("row_label"))
                        .check(jsonPath("$.seats[*].relocMode").optional.saveAs("relocMode"))
                        .check(jsonPath("$.seats[*].isPreview").optional.saveAs("isPreview"))
                        .check(jsonPath("$.seats[*].seatDonationAmount").optional.saveAs("seatDonationAmount"))
                        .check(jsonPath("$.seats[*].imgUrl").optional.saveAs("vvimgUrl"))



  	val getUpgradelist = scenario("Upgrade List")
	 	.exec(getUpgrade).pause(2)

	
}