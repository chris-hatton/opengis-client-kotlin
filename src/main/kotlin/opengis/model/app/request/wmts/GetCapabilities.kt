package opengis.model.app.request.wmts

import opengis.model.app.UpdateSequence
import opengis.model.app.request.GetResourceRepresentation
import opengis.model.transport.xml.wmts.WmtsCapabilities

/**
 * WMTS GetCapabilities operation request
 * Section 7.2.2.1
 * http://portal.opengeospatial.org/files/?artifact_id=35326
 */
class GetCapabilities(
        val updateSequence  : UpdateSequence? = null
) : WebMapTileServiceRequest<WmtsCapabilities>(), GetResourceRepresentation {
    override val requestIdentifier: String = "GetCapabilities"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        parameters.apply {
            //add("FORMAT" to format.toString())
            updateSequence?.let { "UPDATESEQUENCE" to it }
        }
    }
}