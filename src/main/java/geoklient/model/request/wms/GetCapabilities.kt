package geoklient.model.request.wms

import geoklient.model.MimeType
import geoklient.model.UpdateSequence

/**
 *
 */
class GetCapabilities(
        val format          : MimeType,
        val updateSequence  : UpdateSequence? = null
) : WebMapServiceRequest() {
    override val requestIdentifier: String = "GetCapabilities"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        parameters.apply {
            add("FORMAT" to format.toString())
            updateSequence?.let { "UPDATESEQUENCE" to it }
        }
    }
}