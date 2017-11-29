package opengis.model.app

import opengis.model.app.request.OpenGisRequest
import opengis.model.app.request.wms.GetCapabilities as GetWmsCapabilities
import opengis.model.app.request.wmts.GetCapabilities as GetWmtsCapabilities
import opengis.model.app.request.wfs.GetCapabilities as GetWfsCapabilities
import opengis.model.app.request.wcs.GetCapabilities as GetWcsCapabilities
import opengis.model.app.request.csw.GetCapabilities as GetCswCapabilities
import opengis.model.xml.csw.CswCapabilities
import opengis.model.xml.wcs.WcsCapabilities
import opengis.model.xml.wfs.WfsCapabilities
import opengis.model.xml.wms.WmsCapabilities
import opengis.model.xml.wmts.WmtsCapabilities
import java.net.URL

/**
 * Created by Chris on 23/09/2017.
 */
sealed class OpenGisService<Capabilities>(val url: URL) {

    abstract fun getCapabilities() : OpenGisRequest<Capabilities>

    class WebFeatureService(url: URL) : OpenGisService<WfsCapabilities>(url) {
        override fun getCapabilities() = GetWfsCapabilities()
    }

    class WebMapService(url: URL) : OpenGisService<WmsCapabilities>(url) {
        override fun getCapabilities() = GetWmsCapabilities()
    }

    class WebMapTileService(url: URL) : OpenGisService<WmtsCapabilities>(url) {
        override fun getCapabilities() = GetWmtsCapabilities()
    }

    class WebCoverageService(url: URL) : OpenGisService<WcsCapabilities>(url) {
        override fun getCapabilities() = GetWcsCapabilities()
    }

    class CatalogueServicesForWeb(url: URL) : OpenGisService<CswCapabilities>(url) {
        override fun getCapabilities() = GetCswCapabilities()
    }
}