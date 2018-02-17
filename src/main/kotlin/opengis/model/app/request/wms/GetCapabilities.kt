package opengis.model.app.request.wms

import opengis.model.app.UpdateSequence
import opengis.model.transport.xml.wms.WmsCapabilities

/**
 *
 */
class GetCapabilities(
        val updateSequence  : UpdateSequence? = null
) : WebMapServiceRequest<WmsCapabilities>() {
    override val requestIdentifier: String = "GetCapabilities"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        parameters.apply {
            updateSequence?.let { "UPDATESEQUENCE" to it }
        }
    }
}