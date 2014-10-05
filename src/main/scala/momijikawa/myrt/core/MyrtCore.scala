package momijikawa.myrt.core

import akka.actor.{ActorRef, Actor}
import akka.agent.Agent
import momijikawa.myrt.model.{MyrtDataStore, MyrtStatus}
import momijikawa.myrt.util.Sha1._
import scala.concurrent.future
import momijikawa.myrt.core.message._
import momijikawa.myrt.core.model.ChunkKey
import scalaz._
import Scalaz._

class MyrtCore(statusAgent: Agent[MyrtStatus])(implicit val executionContext: scala.concurrent.ExecutionContext) extends Actor {
  def receive = {
    case FindNode(query) =>
    case Get(key) =>
      // senderの保存が必要（future操作中は意味合いが変わる？）
      val sndr = sender
      statusAgent.future().map(st => sndr ! st.data.stock.get(key))
    case PredCheck =>
      // TODO: check whether told node is more suitable
      // TODO: change predecessor if suitable
    case Set(chunk) =>
      val key = ChunkKey(chunk.data.sha1Seq)
      statusAgent.send {
        status =>
          status.copy(data = new MyrtDataStore(status.data.stock.updated(key, chunk)))
      }

    case WhoAreYou => sender ! statusAgent().id
    case YourPredecessor => sender ! statusAgent().predecessor
    case YourSuccessor => sender ! statusAgent().successors.head
  }
}
