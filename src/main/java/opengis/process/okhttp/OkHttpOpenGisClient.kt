package opengis.process.okhttp

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import opengis.model.app.request.OpenGisRequest
import opengis.process.*
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.IOException
import java.io.InputStream

/**
 * Created by Chris on 21/09/2017.
 */
class OkHttpOpenGisClient(
        private val baseUrl              : HttpUrl,
        private val okHttpClient         : OkHttpClient,
        private val responseDeserializer : OpenGisResponseDeserializer
    ) : DeserializingOpenGisClient, OpenGisResponseDeserializer by responseDeserializer {

    override fun <Result : Any> getBytes(request: OpenGisRequest<Result>, callback: OpenGisClient.Callback<InputStream>) {

        val okHttpCallback = object : okhttp3.Callback {
            override fun onFailure (call: Call, e: IOException    ) = callback.error(e)
            override fun onResponse(call: Call, response: Response) {

                when( response.code() ) {
                    200 -> callback.success( response.body()!!.byteStream() )
                    else -> {
                        val errorXml = response.body()!!.string()
                        println(errorXml)
                        val error = OpenGisClient.Exception.ServerError( xmlString = errorXml )
                        callback.error(error)
                    }
                }
            }
        }

        val call = okHttpClient.newCall( baseUrl = baseUrl, openGisRequest = request )
        call.enqueue(okHttpCallback)
    }
}