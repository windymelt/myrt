package momijikawa.myrt.model

case class NodePairList(list: Seq[IdAccessPointPair]) {
  def head = list.headOption
}
