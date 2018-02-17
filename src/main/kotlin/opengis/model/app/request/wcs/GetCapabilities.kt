package opengis.model.app.request.wcs

import opengis.model.transport.xml.wcs.WcsCapabilities

/**
 * Created by Chris on 29/11/2017.
 */
class GetCapabilities : WebCoverageServiceRequest<WcsCapabilities>() {
    override val requestIdentifier: String = "WCS"
}
