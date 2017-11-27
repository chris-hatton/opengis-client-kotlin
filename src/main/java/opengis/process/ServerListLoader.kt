package opengis.process

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import opengis.model.app.OpenGisServer
import opengis.model.app.OpenGisServerInfo
import java.io.InputStreamReader
import java.net.URL

/**
 * Loads a simple online list of OpenGIS servers and the services they offer, in the format:
 *
 * {
 *      "name" : "Victorian State Government GeoServer",
 *      "services" : {
 *          "wfs"  : "http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/wfs",
 *          "wms"  : "http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/wms",
 *          "wmts" : "http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/wmts",
 *          "wcs"  : "http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/wcs"
 *      }
 *  }
 */
object ServerListLoader {

    sealed class Exception : kotlin.Exception() {
        object ListUnavailable : Exception()
    }

    /**
     * Synchronously load a list of OpenGisServer's from the list at the given URL.
     * This method is not intended for production applications.
     */
    fun load( listURL: URL ) : List<OpenGisServer> {
        val request = Request.Builder().url(listURL).build()
        val gson = GsonBuilder().create()
        val inputStream = OkHttpClient().newCall(request).execute().body()?.byteStream() ?: throw Exception.ListUnavailable
        inputStream.use {
            val reader = InputStreamReader(it)
            val typeToken : TypeToken<List<OpenGisServerInfo>> = object : TypeToken<List<OpenGisServerInfo>>() {}
            val serverInfo : List<OpenGisServerInfo> = gson.fromJson(reader,typeToken.type)
            return serverInfo.map(OpenGisServerInfo::toOpenGisServer)
        }
    }

}