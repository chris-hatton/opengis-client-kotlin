package opengis.model.app

import opengis.model.app.request.OpenGisRequest
import opengis.model.app.request.csw.CatalogueServiceForWebRequest
import opengis.model.app.request.wcs.WebCoverageServiceRequest
import opengis.model.app.request.wfs.WebFeatureServiceRequest
import opengis.model.app.request.wms.WebMapServiceRequest
import opengis.model.app.request.wmts.WebMapTileServiceRequest
import opengis.model.app.request.wms.GetCapabilities as GetWmsCapabilities
import opengis.model.app.request.wmts.GetCapabilities as GetWmtsCapabilities
import opengis.model.app.request.wfs.GetCapabilities as GetWfsCapabilities
import opengis.model.app.request.wcs.GetCapabilities as GetWcsCapabilities
import opengis.model.app.request.csw.GetCapabilities as GetCswCapabilities
import opengis.model.transport.xml.csw.CswCapabilities
import opengis.model.transport.xml.wcs.WcsCapabilities
import opengis.model.transport.xml.wfs.WfsCapabilities
import opengis.model.transport.xml.wms.WmsCapabilities
import opengis.model.transport.xml.wmts.WmtsCapabilities
import opengis.process.OpenGisRequestProcessor

/**
 * Created by Chris on 23/09/2017.
 */
sealed class OpenGisService<Capabilities> {

    abstract fun getCapabilities() : OpenGisRequest<Capabilities>

    object WebFeatureService : OpenGisService<WfsCapabilities>() {
        override fun getCapabilities() = GetWfsCapabilities()
    }

    object WebMapService : OpenGisService<WmsCapabilities>() {
        override fun getCapabilities() = GetWmsCapabilities()
    }

    object WebMapTileService : OpenGisService<WmtsCapabilities>() {
        override fun getCapabilities() = GetWmtsCapabilities()
    }

    object WebCoverageService : OpenGisService<WcsCapabilities>() {
        override fun getCapabilities() = GetWcsCapabilities()
    }

    object CatalogueServicesForWeb : OpenGisService<CswCapabilities>() {
        override fun getCapabilities() = GetCswCapabilities()
    }

    companion object {

        fun values() : Set<OpenGisService<*>> = setOf(
            WebFeatureService,
            WebMapService,
            WebMapTileService,
            WebCoverageService,
            CatalogueServicesForWeb
        )

        fun <Result> `for`( request: OpenGisRequest<Result>) : OpenGisService<*> = when(request){
            is WebMapServiceRequest<*>          -> OpenGisService.WebMapService
            is WebMapTileServiceRequest<*>      -> OpenGisService.WebMapTileService
            is WebFeatureServiceRequest<*>      -> OpenGisService.WebFeatureService
            is WebCoverageServiceRequest<*>     -> OpenGisService.WebCoverageService
            is CatalogueServiceForWebRequest<*> -> OpenGisService.WebMapService

            else -> throw OpenGisRequestProcessor.Exception.UnhandledRequestType
        }
    }
}