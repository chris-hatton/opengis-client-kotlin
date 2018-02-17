package opengis.model.app.request.wmts

import opengis.model.app.Style
import opengis.model.app.request.GetResourceRepresentation

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

     override fun collateParameters( parameters: MutableList<Pair<String,String>> ) {
        super.collateParameters(parameters)
        parameters.apply {
            add("Layer"         to layer.name         )
            add("Style"         to style.name         )
            add("Format"        to format             )
            add("TileMatrixSet" to tileMatrixSet      )
            add("TileMatrix"    to tileMatrix         )
            add("TileRow"       to tileRow.toString() )
            add("TileCol"       to tileCol.toString() )
        }
    }


    override val requestIdentifier: String = "GetTile"
}