package momijikawa

import momijikawa.myrt.model.{ComposedData, KvsKey, NodeId}
import momijikawa.myrt.util.Sha1._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaz.Scalaz._
import scalaz._

class Myrt {
  def init(id: NodeId) = {
    // do nothing
  }

  def get(id: KvsKey): Future[\/[String, ComposedData]] = {
    Future(ComposedData(Seq(8, 4, 9, 2)).right)
  }

  def set(data: ComposedData): Future[\/[String, KvsKey]] = {
    Future(KvsKey(data.value.sha1Seq).right)
  }
}
