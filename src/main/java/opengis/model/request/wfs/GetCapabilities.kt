package opengis.model.request.wfs

import opengis.model.MimeType
import opengis.model.UpdateSequence
import opengis.model.response.ServiceMetaData

/**
 *
 */
class GetCapabilities(
        val format          : MimeType,
        val updateSequence  : UpdateSequence? = null
) : WebFeatureServiceRequest<ServiceMetaData>() {
    override val requestIdentifier: String = "GetCapabilities"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        parameters.apply {
            add("FORMAT" to format.toString())
            updateSequence?.let { "UPDATESEQUENCE" to it }
        }
    }
}