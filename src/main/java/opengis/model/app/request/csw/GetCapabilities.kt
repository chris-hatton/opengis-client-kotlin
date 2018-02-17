package opengis.model.app.request.csw

import opengis.model.transport.xml.csw.CswCapabilities

/**
 * Created by Chris on 24/11/2017.
 */
class GetCapabilities : CatalogueServiceForWebRequest<CswCapabilities>() {
    override val requestIdentifier: String
        get() = "CSW"
}
