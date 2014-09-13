package momijikawa.myrt

import momijikawa.Myrt
import momijikawa.myrt.model.{ComposedData, KvsKey, NodeId}
import momijikawa.myrt.util.Base64._
import org.specs2.mutable._

import scala.concurrent.Await
import scala.concurrent.duration._
import scalaz.Scalaz._
import scalaz._

class MyrtSpec extends Specification {
  "init" should {
    "nodeIdを受け取る" in {
      val myrt = new Myrt
      myrt.init(NodeId(Seq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
      success
    }
  }
  "set" should {
    "正しく取り出せる" in {
      val myrt = new Myrt
      myrt.init(NodeId(Seq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
      val result = Await.result[\/[String, KvsKey]](myrt.set(ComposedData("mogamin".toCharArray.map(_.toByte))), 1000 milli)
      result.isRight === true
      result match {
        case \/-(key) ⇒
          key.value.base64String === "1y3Xk3M5ls37ngA0egnuYjvEd7Y=" // mogamin
      }
    }
  }
  "get" should {
    "正しく取り出せる" in {
      todo
    }
  }
}
