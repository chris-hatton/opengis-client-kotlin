package opengis.process.okhttp

import okhttp3.OkHttpClient
import opengis.model.request.OpenGisRequest
import opengis.process.Callback
import opengis.process.DeserializingOpenGisClient
import opengis.process.OpenGisClient

/**
 * Created by Chris on 21/09/2017.
 */
class OkHttpOpenGisClient( val okHttpClient: OkHttpClient ) : DeserializingOpenGisClient {

    override fun <Result> getBytesSync(request: OpenGisRequest<Result>): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Result> getBytesAsync(request: OpenGisRequest<Result>, callback: Callback<ByteArray>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Result> deserializeResult(bytes: ByteArray): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}