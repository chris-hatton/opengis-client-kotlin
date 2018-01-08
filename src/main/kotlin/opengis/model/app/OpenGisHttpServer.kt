package opengis.model.app

import opengis.model.app.request.OpenGisRequest
import opengis.model.app.request.csw.CatalogueServiceForWebRequest
import opengis.model.app.request.wcs.WebCoverageServiceRequest
import opengis.model.app.request.wfs.WebFeatureServiceRequest
import opengis.model.app.request.wms.WebMapServiceRequest
import opengis.model.app.request.wmts.WebMapTileServiceRequest
import java.net.URL

data class OpenGisHttpServer(private val serviceUrls: Map<OpenGisService<*>,URL>) {

    constructor(vararg serviceUrls: Pair<OpenGisService<*>,URL>)
            : this( serviceUrls.toMap() )

    sealed class Exception : kotlin.Exception() {
        data class UnsupportedRequestType( val request: OpenGisRequest<*> ) : Exception()
        data class UnsupportedService( val service: OpenGisService<*> ) : Exception()
    }

    constructor(
        wmsUrlString  : String? = null,
        wmtsUrlString : String? = null,
        wfsUrlString  : String? = null,
        wcsUrlString  : String? = null,
        cswUrlString  : String? = null
    ) : this( serviceUrls = arrayOf<Pair<OpenGisService<*>,URL>?>(
            wmsUrlString ?.let { OpenGisService.WebMapService           to URL(it) },
            wmtsUrlString?.let { OpenGisService.WebMapTileService       to URL(it) },
            wfsUrlString ?.let { OpenGisService.WebFeatureService       to URL(it) },
            wcsUrlString ?.let { OpenGisService.WebCoverageService      to URL(it) },
            cswUrlString ?.let { OpenGisService.CatalogueServicesForWeb to URL(it) }
        ).filterNotNull().toMap<OpenGisService<*>,URL>()
    )

    val services : Set<OpenGisService<*>> = serviceUrls.keys

    fun <Result> url( request: OpenGisRequest<Result>) : URL {
        val service = when(request){
            is WebMapServiceRequest<*>          -> OpenGisService.WebMapService
            is WebMapTileServiceRequest<*>      -> OpenGisService.WebMapTileService
            is WebFeatureServiceRequest<*>      -> OpenGisService.WebFeatureService
            is WebCoverageServiceRequest<*>     -> OpenGisService.WebCoverageService
            is CatalogueServiceForWebRequest<*> -> OpenGisService.WebMapService
            else -> throw Exception.UnsupportedRequestType( request )
        }

        return serviceUrls[service] ?: throw Exception.UnsupportedService( service )
    }
}