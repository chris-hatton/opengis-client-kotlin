package opengis.model.app

import com.google.gson.annotations.SerializedName
import java.net.URL

data class OpenGisServerInfo(
    @SerializedName("name"    ) val name     : String = "",
    @SerializedName("services") val services : Services = Services()
) {
    data class Services(
        @SerializedName("wfs" ) val wfsUrlString  : String? = null,
        @SerializedName("wms" ) val wmsUrlString  : String? = null,
        @SerializedName("wmts") val wmtsUrlString : String? = null,
        @SerializedName("wcs" ) val wcsUrlString  : String? = null,
        @SerializedName("cws" ) val cwsUrlString  : String? = null
    )

    fun toOpenGisServer() : OpenGisHttpServer {
        val serverEntries = setOf<Pair<OpenGisService<*>,URL>?>(
            services.wfsUrlString ?.let { OpenGisService.WebFeatureService       to URL(it) },
            services.wmsUrlString ?.let { OpenGisService.WebMapService           to URL(it) },
            services.wmtsUrlString?.let { OpenGisService.WebMapTileService       to URL(it) },
            services.wcsUrlString ?.let { OpenGisService.WebCoverageService      to URL(it) },
            services.cwsUrlString ?.let { OpenGisService.CatalogueServicesForWeb to URL(it) }
        )

        val serverMap = mutableMapOf<OpenGisService<*>,URL>().apply {
            putAll(serverEntries.filterNotNull())
        }

        return OpenGisHttpServer( serverMap )
    }
}
