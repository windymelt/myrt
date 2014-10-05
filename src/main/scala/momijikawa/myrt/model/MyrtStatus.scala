package momijikawa.myrt.model

case class MyrtStatus(id: IdAccessPointPair, successors: NodePairList, fingers: NodePairList, predecessor: Option[IdAccessPointPair], data: MyrtDataStore) {

}
