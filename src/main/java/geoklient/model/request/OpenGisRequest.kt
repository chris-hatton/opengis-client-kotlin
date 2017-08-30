package geoklient.model.request

import okhttp3.HttpUrl

abstract class OpenGisRequest( val version : String = OpenGisRequest.version ) {

    companion object {
        val version = "1.3.0"
    }

    fun buildUrl( baseUrl: HttpUrl ) : HttpUrl {
        val parameters : MutableList<Pair<String,String>> = mutableListOf()
        collateParameters( parameters )
        return with( baseUrl.newBuilder() ) {
            parameters.forEach { (name,value) -> addQueryParameter(name,value) }
            build()
        }
    }

    open protected fun collateParameters( parameters: MutableList<Pair<String,String>> ) {
        parameters.apply {
            add("VERSION" to version          )
            add("SERVICE" to serviceIdentifier)
            add("REQUEST" to requestIdentifier)
        }
    }

    abstract val serviceIdentifier : String
    abstract val requestIdentifier : String
}