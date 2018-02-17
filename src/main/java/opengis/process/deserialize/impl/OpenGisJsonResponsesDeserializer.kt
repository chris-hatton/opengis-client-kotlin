package opengis.process.deserialize.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import geojson.Feature
import geojson.FeatureCollection
import opengis.model.app.request.OpenGisRequest
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.reflect.KClass

class OpenGisJsonResponsesDeserializer(
        val gson: Gson = GsonBuilder().create()
    ) : OpenGisResponseDeserializer {

    override fun <Result:Any> deserializeResult(
        bytes: InputStream,
        request: OpenGisRequest<Result>,
        resultClass: KClass<Result>
    ) : Result = if( supportedTypes.contains(resultClass) ) {
        val byteReader = InputStreamReader(bytes)
        gson.fromJson<Result>( byteReader, resultClass.java )
    } else throw OpenGisResponseDeserializer.Exception.UnhandledType

    private val supportedTypes : List<KClass<*>> = listOf(
        FeatureCollection::class,
        Feature::class
    )
}