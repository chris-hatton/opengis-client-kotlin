package geoklient.model.request.wms

import geoklient.model.ExceptionFormat
import geoklient.model.Layer
import geoklient.model.MimeType

/**
 *
 */
class GetFeatureInfo(
        val mapRequest      : GetMap,
        val queryLayers     : List<Layer>,
        val infoFormat      : MimeType,
        val featureCount    : Int?             = null,
        val pixelColumn     : Int,
        val pixelRow        : Int,
        val exceptionFormat : ExceptionFormat? = null
) : WebMapServiceRequest() {
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