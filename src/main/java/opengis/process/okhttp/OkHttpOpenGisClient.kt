package opengis.process.okhttp

import kimage.model.Image
import kimage.model.pixel.RGB
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import opengis.model.request.OpenGisRequest
import opengis.process.*
import java.io.IOException
import kotlin.reflect.KClass

/**
 * Created by Chris on 21/09/2017.
 */
class OkHttpOpenGisClient<Image:Any>(
        private val baseUrl              : HttpUrl,
        private val okHttpClient         : OkHttpClient,
        private val responseDeserializer : OpenGisResponseDeserializer
    ) : DeserializingOpenGisClient, OpenGisResponseDeserializer by responseDeserializer {

    constructor(
        baseUrl           : HttpUrl,
        okHttpClient      : OkHttpClient = OkHttpClient.Builder().build(),
        imageDeserializer : Deserializer<Image>,
        imageClass        : KClass<Image>
    ) : this(
        baseUrl              = baseUrl,
        okHttpClient         = okHttpClient,
        responseDeserializer = DefaultOpenGisResponseDeserializer(imageDeserializer,imageClass)
    )

    override fun <Result : Any> getBytes(request: OpenGisRequest<Result>, callback: OpenGisClient.Callback<ByteArray>) {

        val okHttpCallback = object : okhttp3.Callback {
            override fun onFailure (call: Call, e: IOException    ) = callback.error(e)
            override fun onResponse(call: Call, response: Response) {

                when( response.code() ) {
                    200 -> callback.success( response.body()!!.bytes() )
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