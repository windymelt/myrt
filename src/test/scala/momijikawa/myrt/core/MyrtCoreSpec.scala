package momijikawa.myrt.core

import akka.actor._
import akka.agent.Agent
import akka.testkit._
import scala.concurrent.duration._
import momijikawa.myrt.core.message.Get
import momijikawa.myrt.core.model.{ChunkedData, ChunkKey}
import momijikawa.myrt.model._
import org.specs2.mutable._
import org.specs2.time.NoTimeConversions

import scala.collection.immutable.HashMap

abstract class AkkaTestkitSpecs2Support extends TestKit(ActorSystem("test-MyrtCoreSpec")) with After with ImplicitSender {
  // make sure we shut down the actor system after all tests have run
  def after = system.shutdown()
}

class MyrtCoreSpec extends Specification with NoTimeConversions {
  val dummyPair = IdAccessPointPair(NodeId(Seq(0)), NodeAccessPoint("test"))
  val dummyList = NodePairList(Seq(dummyPair))

  "Get" should {
    "データストアに値があるときはSome(値)を返す" in new AkkaTestkitSpecs2Support {
      implicit val context = system.dispatcher

      val agt = Agent(MyrtStatus(dummyPair, dummyList, dummyList, Some(dummyPair), new MyrtDataStore(new HashMap[ChunkKey, ChunkedData])))
      agt.send(stat => stat.copy(data = new MyrtDataStore(stat.data.stock.+((ChunkKey(Seq(0, 0, 0, 0)), ChunkedData(Seq(9, 9, 9, 9)))))))

      val myrt = system.actorOf(Props(classOf[MyrtCore], agt, context), "Myrt")

      within(3 seconds) {
        myrt ! Get(ChunkKey(Seq(0, 0, 0, 0)))
        expectMsg(Some(ChunkedData(Seq(9, 9, 9, 9))))
      }
    }
    "データストアに値が無いときはNoneを返す" in new AkkaTestkitSpecs2Support {
      implicit val context = system.dispatcher

      val agt = Agent(MyrtStatus(dummyPair, dummyList, dummyList, Some(dummyPair), new MyrtDataStore(new HashMap[ChunkKey, ChunkedData])))
      val myrt = system.actorOf(Props(classOf[MyrtCore], agt, context), "Myrt")

      within(3 seconds) {
        myrt ! Get(ChunkKey(Seq(0, 0, 0, 0)))
        expectMsg(None)
      }
    }
  }
}
