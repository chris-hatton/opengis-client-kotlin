package opengis.model.app.request.wms

import opengis.model.app.MimeType
import opengis.model.app.UpdateSequence
import opengis.model.app.response.wms.ServiceMetaData

/**
 *
 */
class GetCapabilities(
        val format          : MimeType,
        val updateSequence  : UpdateSequence? = null
) : WebMapServiceRequest<ServiceMetaData>() {
    override val requestIdentifier: String = "GetCapabilities"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        parameters.apply {
            add("FORMAT" to format.toString())
            updateSequence?.let { "UPDATESEQUENCE" to it }
        }
    }
}