package geoklient.model.request.wmts

import geoklient.model.ExceptionFormat
import geoklient.model.Layer
import geoklient.model.MimeType
import geoklient.model.Style
import geoklient.model.request.wms.GetMap
import geoklient.model.request.wms.WebMapServiceRequest

/**
 * WMTS GetFeatureInfo operation request
 * Section 7.2.2.1
 * http://portal.opengeospatial.org/files/?artifact_id=35326
 */
class GetFeatureInfo(
             val mapRequest      : GetMap,
             val queryLayers     : List<Layer>,
             val infoFormat      : MimeType,
             val featureCount    : Int?             = null,
             val pixelColumn     : Int,
             val pixelRow        : Int,
             val exceptionFormat : ExceptionFormat? = null,
    override val layer           : Layer,
    override val style           : Style,
    override val format          : String,
    override val tileMatrixSet   : String,
    override val tileMatrix      : String,
    override val tileRow         : Int,
    override val tileCol         : Int
) : WebMapTileServiceRequest(), TileRequest {
    override val requestIdentifier: String = "GetFeatureInfo"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)

        mapRequest.collateMapParameters(parameters)

        parameters.apply {
            add("QUERY_LAYERS" to queryLayers.joinToString(","))
            add("INFO_FORMAT" to infoFormat.toString())
            featureCount?.let { "FEATURE_COUNT" to it }
            add("I" to pixelColumn.toString())
            add("J" to pixelRow.toString())
            exceptionFormat?.let{ "EXCEPTION_FORMAT" to it }
        }
    }
}