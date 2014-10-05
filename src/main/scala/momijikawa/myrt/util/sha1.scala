package momijikawa.myrt.util

import java.security.MessageDigest

import momijikawa.myrt.util.Base64._

object Sha1 {
  lazy val digest = MessageDigest.getInstance("SHA-1")

  implicit class Sha1SeqConversion(val data: Seq[Byte]) extends AnyVal {
    def sha1Seq: Seq[Byte] = {
      digest.digest(data.toArray)
    }

    def sha1Base64: String = {
      this.sha1Seq.base64String
    }
  }

}
