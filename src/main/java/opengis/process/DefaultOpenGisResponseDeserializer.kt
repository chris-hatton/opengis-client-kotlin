package opengis.process

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import opengis.model.request.OpenGisRequest
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import kotlin.reflect.KClass


typealias Deserializer<Result> = (ByteArray)->Result

class DefaultOpenGisResponseDeserializer<Image:Any> (
        val imageDeserializer : Deserializer<Image>,
        val imageClass        : KClass<Image>,
        val gson              : Gson = GsonBuilder().create()
    ) : OpenGisResponseDeserializer {

    override fun <Result:Any> deserializeResult( bytes: ByteArray, request: OpenGisRequest<Result>, resultClass: KClass<Result>) : Result {

        fun jsonDeserializer( bytes: ByteArray ) : Result {
            val byteInputStream = ByteArrayInputStream(bytes)
            val byteReader      = InputStreamReader(byteInputStream)
            return gson.fromJson<Result>( byteReader, resultClass.java )
        }

        @Suppress("UNCHECKED_CAST")
        return when( resultClass ) {
            ByteArray::class -> bytes as Result
            imageClass       -> imageDeserializer(bytes) as Result
            else             -> jsonDeserializer(bytes)
        }
    }

    sealed class Exception : kotlin.Exception() {
        object UnhandledResultType : Exception()
    }
}