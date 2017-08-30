package geoklient.model.request.wmts

import geoklient.model.Layer
import geoklient.model.Style

/**
 * WMTS GetTile operation request
 * Section 7.2.2.1
 * http://portal.opengeospatial.org/files/?artifact_id=35326
 */
class GetTile(
        override val layer         : Layer,
        override val style         : Style,
        override val format        : String,
        override val tileMatrixSet : String,
        override val tileMatrix    : String,
        override val tileRow       : Int,
        override val tileCol       : Int
    ) : WebMapTileServiceRequest(), TileRequest {

    //TODO: Sample Dimension?

    override val requestIdentifier: String = "GetTile"



    /*

     */

}