package opengis.model.app.request

/**
 * Base class for requests to all OpenGIS standard webservices.
 * These seem to follow an informal protocol that all specify
 * Version, Service and type of Request.
 */
abstract class OpenGisRequest<Result>( val version : String = OpenGisRequest.Companion.version ) {

    companion object {
        val version = "1.3.0"
    }

    fun getParameters() : List<Pair<String,String>> {
        val parameters = mutableListOf<Pair<String,String>>()
        collateParameters( parameters )
        return parameters
    }

    open protected fun collateParameters( parameters: MutableList<Pair<String,String>> ) {
        val v = version
        parameters.apply {
            add("VERSION" to v          )
            add("SERVICE" to serviceIdentifier)
            add("REQUEST" to requestIdentifier)
        }
    }

    abstract val serviceIdentifier : String
    abstract val requestIdentifier : String
}