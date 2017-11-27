package opengis.model.app

import com.google.gson.annotations.SerializedName
import java.net.URL

data class OpenGisServerInfo(
    @SerializedName("name"    ) val name     : String,
    @SerializedName("services") val services : Services
) {
    data class Services(
        @SerializedName("wfs" ) val wfsUrlString  : String?,
        @SerializedName("wms" ) val wmsUrlString  : String?,
        @SerializedName("wmts") val wmtsUrlString : String?,
        @SerializedName("wcs" ) val wcsUrlString  : String?,
        @SerializedName("cws" ) val cwsUrlString  : String?
    )

    fun toOpenGisServer() : OpenGisServer {
        val wfs  = services.wfsUrlString ?.let(::URL)?.let { OpenGisService.WebFeatureService      (it) }
        val wms  = services.wmsUrlString ?.let(::URL)?.let { OpenGisService.WebMapService          (it) }
        val wmts = services.wmtsUrlString?.let(::URL)?.let { OpenGisService.WebMapTileService      (it) }
        val wcs  = services.wcsUrlString ?.let(::URL)?.let { OpenGisService.WebCoverageService     (it) }
        val cws  = services.cwsUrlString ?.let(::URL)?.let { OpenGisService.CatalogueServicesForWeb(it) }

        return arrayOf<OpenGisService<*>?>(wfs,wms,wmts,wcs,cws).filterNotNull().let {
            OpenGisServer( services = it.toSet() )
        }
    }
}
