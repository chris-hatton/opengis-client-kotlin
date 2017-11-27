package opengis.model.app

import opengis.model.app.request.OpenGisRequest
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
        override fun getCapabilities() : OpenGisRequest<WfsCapabilities> {
            TODO()
        }
    }

    class WebMapService(url: URL) : OpenGisService<WmsCapabilities>(url) {
        override fun getCapabilities(): OpenGisRequest<WmsCapabilities> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class WebMapTileService(url: URL) : OpenGisService<WmtsCapabilities>(url) {
        override fun getCapabilities(): OpenGisRequest<WmtsCapabilities> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class WebCoverageService(url: URL) : OpenGisService<WcsCapabilities>(url) {
        override fun getCapabilities(): OpenGisRequest<WcsCapabilities> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class CatalogueServicesForWeb(url: URL) : OpenGisService<CswCapabilities>(url) {
        override fun getCapabilities(): OpenGisRequest<CswCapabilities> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}