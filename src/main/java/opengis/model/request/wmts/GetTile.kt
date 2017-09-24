package opengis.model.request.wmts

import kimage.model.Image
import kimage.model.pixel.RGB
import opengis.model.Layer
import opengis.model.Style
import opengis.model.request.GetResourceRepresentation

/**
 * WMTS GetTile operation request
 * Section 7.2.2.1
 * http://portal.opengeospatial.org/files/?artifact_id=35326
 */
data class GetTile<Image>(
        override val layer         : Layer,
        override val style         : Style,
        override val format        : String,
        override val tileMatrixSet : String,
        override val tileMatrix    : String,
        override val tileRow       : Int,
        override val tileCol       : Int
    ) : WebMapTileServiceRequest<Image>(), TileRequest, GetResourceRepresentation {

    //TODO: Sample Dimension?

    override val requestIdentifier: String = "GetTile"
}