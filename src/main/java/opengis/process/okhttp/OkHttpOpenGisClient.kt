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
    ) : this(baseUrl,okHttpClient, DefaultOpenGisResponseDeserializer(imageDeserializer,imageClass))

    override fun <Result : Any> getBytes(request: OpenGisRequest<Result>, callback: Callback<ByteArray>) {

        val okHttpCallback = object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO()
            }

            override fun onResponse(call: Call, response: Response) {
                val bytes : ByteArray = response.body()!!.bytes()
                callback(bytes)
            }
        }

        okHttpClient.newCall( baseUrl = baseUrl, openGisRequest = request ).enqueue(okHttpCallback)
    }
}