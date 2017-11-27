package opengis.process

interface Callback<in Result> {
    fun success( result: Result )
    fun error( error: Throwable )
}
