import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetBarcodeImage {
  
	val getBarcodeImage = http("Barcode Image")
            .get("${barcode_url}")
            .check(status.in(200,304))
            .check(bodyBytes.exists)
                        
	val getBarcode = scenario("Barcode Image")
	         .exec(getBarcodeImage).pause(2)
}