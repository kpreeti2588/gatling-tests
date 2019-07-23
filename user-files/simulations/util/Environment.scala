import java.util

object Environment { 
	val baseURL = scala.util.Properties.envOrElse("baseURL", System.getProperty("host"))
	//val csv = scala.util.Properties.envOrElse("csv", "proxysql.csv")

  val csv = scala.util.Properties.envOrElse("csv", "TicketInvoice.csv")

  
  val csv1 = scala.util.Properties.envOrElse("csv1", "Groupnames.csv")
	val users = scala.util.Properties.envOrElse("numberOfUsers", "5000")
  //val VVAPI = "https://tm-sa-api.nonprod-tmaws.io/qa"
  val VVAPI ="https://vv-app-preprod.ticketmaster.com"
  	val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "60000") // in milliseconds
  	val runType = scala.util.Properties.envOrElse("runType", System.getProperty("runType"))
  	//val venueId = 506
    val venueId = 10049
    val dataState = 1
    val memberId="IOMEDIA_PREPROD.90003.3759"
    val namBaseUrl="http://nam.prd214.preprodpci3.us-east-1.tktm.io/loadtest"
    val datatoken="2727ace5e62e5ab6d1d4fa3f67648dd1UFh4ek1lSnUxcUR0bjVPRVplbHN6U29lZFVmZ2lzcm54TFE5Y3RTNlI5Zw=="
    //val sectionname = "102,103,145,146,147,148,151,203"
    //val sectionname = "102,103,106,107,108,109,110,111,112,113,115,118,119,120,121,123,124,125,126,127,128,131,132,133,134,135,136,137,138,139,140,143,144,145,146,147,148,149,150,151,201,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344"
    val sectionname = "102,103,106,107,108,109,110,111,112,113,115,118,119,120,121,123,124,125,126,127,128,131,132,133,134,135,136,137,138,139,140,143,144,145,146,147,148"
   // val dataState = 30
    val srcSysName = "IOMEDIA"
    val targetEvent ="15IOFULL"
    val language = "en"
    var groupName1 = "g7"
    var groupName2 = "g2"
    var groupName3 = "g16"
    val promoCode = "DANA"
    /* Seat details for validateHoldSeat api */
  /* Seat 1 data*/
  val seatPrice1=250
  val seatPriceCode1="F"
  val sectionName1="Section_304"
  val seatNum1="6"
  val row1="A"
  val numSeat1=1
  val ticketType1="_A"

  /* Seat 2 data*/
  val seatPrice2=250
  val seatPriceCode2="F"
  val sectionName2="Section_304"
  val seatNum2="5"
  val row2="A"
  val numSeat2=1
  val ticketType2="_A" 
  
  /* Seat 3 data*/
  val seatPrice3=500
  val seatPriceCode3="E"
  val sectionName3="Section_307"
  val seatNum3="22"
  val row3="F"
  val numSeat3=1
  val ticketType3="_A"
  
 /* Seat 4 data*/
  val seatPrice4=500
  val seatPriceCode4="E"
  val sectionName4="Section_307"
  val seatNum4="21"
  val row4="F"
  val numSeat4=1
  val ticketType4="_A" 
  
  /* Seat 5 data*/
  val seatPrice5=1000
  val seatPriceCode5="D"
  val sectionName5="Section_309"
  val seatNum5="6"
  val row5="K"
  val numSeat5=1
  val ticketType5="_A" 
}
