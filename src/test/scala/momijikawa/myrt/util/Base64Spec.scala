package momijikawa.myrt.util

import momijikawa.myrt.util.Base64._
import org.specs2.mutable._

class Base64Spec extends Specification {
  "Base64Conversion" in {
    "base64String" should {
      "正しい値を返す" in {
        "mogamin".toCharArray.map(_.toByte).toSeq.base64String === "bW9nYW1pbg=="
      }
    }
  }
}