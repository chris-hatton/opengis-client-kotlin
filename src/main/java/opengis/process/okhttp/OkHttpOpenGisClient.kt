package opengis.process.okhttp

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import opengis.model.app.OpenGisServer
import opengis.model.app.OpenGisService
import opengis.model.app.request.OpenGisRequest
import opengis.process.*
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.IOException
import java.io.InputStream
import java.net.URL
import kotlin.reflect.KClass

/**
 * Created by Chris on 21/09/2017.
 */
class OkHttpOpenGisClient(
        server                           : OpenGisServer,
        private val okHttpClient         : OkHttpClient = OkHttpClient(),
        private val responseDeserializer : OpenGisResponseDeserializer = OpenGisResponseDeserializer.createDefault()
    ) : OpenGisHttpClient(server), OpenGisResponseDeserializer by responseDeserializer {

    override fun <Result : Any> getBytes(url: URL, request: OpenGisRequest<Result>, callback: Callback<InputStream>) {

        val okHttpCallback = object : okhttp3.Callback {
            override fun onFailure (call: Call, e: IOException    ) = callback(Outcome.Error(e))
            override fun onResponse(call: Call, response: Response) {

                when( response.code() ) {
                    200 -> callback(Outcome.Success( response.body()!!.byteStream() ))
                    else -> {
                        val errorXml = response.body()!!.string()
                        println(errorXml)
                        val error = OpenGisRequestProcessor.Exception.ServerError( xmlString = errorXml )
                        callback(Outcome.Error(error))
                    }
                }
            }
        }

        val httpUrl = HttpUrl.get(url)!!

        val call = okHttpClient.newCall( baseUrl = httpUrl, openGisRequest = request )
        call.enqueue(okHttpCallback)
    }
}