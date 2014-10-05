package momijikawa.myrt.core.message

import momijikawa.myrt.model.NodeId

case class FindNode(val query: NodeId) extends AnyVal {

}
