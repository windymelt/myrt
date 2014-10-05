package momijikawa.myrt.util

import org.apache.commons.codec.binary.{ Base64 ⇒ Base64Apache }

object Base64 {
  implicit class Base64Conversion(data: Seq[Byte]) {
    def base64String: String = {
      new String(Base64Apache.encodeBase64(data.toArray))
    }
  }
}
