package geoklient.model.request.wmts

import geoklient.model.Layer
import geoklient.model.Style

/**
 * Created by Chris on 24/08/2017.
 */
interface TileRequest {

    val layer         : Layer
    val style         : Style
    val format        : String
    val tileMatrixSet : String
    val tileMatrix    : String
    val tileRow       : Int
    val tileCol       : Int

    //val Sample dimension : String //TODO ?
}

