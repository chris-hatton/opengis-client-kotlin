package opengis.model.request.wmts

import opengis.model.ExceptionFormat
import opengis.model.Layer
import opengis.model.MimeType
import opengis.model.Style
import opengis.model.request.GetResourceRepresentation
import opengis.model.request.wms.GetMap
import opengis.model.response.wmts.FeatureInfo

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
) : WebMapTileServiceRequest<FeatureInfo>(), TileRequest, GetResourceRepresentation {
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