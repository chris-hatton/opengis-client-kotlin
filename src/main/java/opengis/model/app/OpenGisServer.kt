package opengis.model.app

import opengis.model.app.request.OpenGisRequest
import opengis.model.app.request.csw.CatalogueServiceForWebRequest
import opengis.model.app.request.wcs.WebCoverageServiceRequest
import opengis.model.app.request.wfs.WebFeatureServiceRequest
import opengis.model.app.request.wms.WebMapServiceRequest
import opengis.model.app.request.wmts.WebMapTileServiceRequest
import opengis.process.OpenGisRequestProcessor
import java.net.URL

/**
 * Created by Chris on 24/11/2017.
 */
data class OpenGisServer(val services: Set<out OpenGisService<*>>) {

    constructor(
        wmsUrlString  : String? = null,
        wmtsUrlString : String? = null,
        wfsUrlString  : String? = null,
        wcsUrlString  : String? = null,
        cswUrlString  : String? = null
    ) : this( services = arrayOf<OpenGisService<*>?>(
            wmsUrlString ?.let { OpenGisService.WebMapService          ( url = URL(it) ) },
            wmtsUrlString?.let { OpenGisService.WebMapTileService      ( url = URL(it) ) },
            wfsUrlString ?.let { OpenGisService.WebFeatureService      ( url = URL(it) ) },
            wcsUrlString ?.let { OpenGisService.WebCoverageService     ( url = URL(it) ) },
            cswUrlString ?.let { OpenGisService.CatalogueServicesForWeb( url = URL(it) ) }
        ).filterNotNull().toSet<OpenGisService<*>>()
    )

    fun <Result> service( request: OpenGisRequest<Result>) : OpenGisService<*>? = when(request){
        is WebMapServiceRequest<*>          -> services.find { it is OpenGisService.WebMapService      }
        is WebMapTileServiceRequest<*>      -> services.find { it is OpenGisService.WebMapTileService  }
        is WebFeatureServiceRequest<*>      -> services.find { it is OpenGisService.WebFeatureService  }
        is WebCoverageServiceRequest<*>     -> services.find { it is OpenGisService.WebCoverageService }
        is CatalogueServiceForWebRequest<*> -> services.find { it is OpenGisService.WebMapService      }

        else -> throw OpenGisRequestProcessor.Exception.UnhandledRequestType
    }
}