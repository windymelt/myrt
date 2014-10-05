package momijikawa.myrt.model

import momijikawa.myrt.core.model.{ChunkedData, ChunkKey}

class MyrtDataStore(val stock: Map[ChunkKey, ChunkedData]) {

}
