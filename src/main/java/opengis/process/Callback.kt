package opengis.process

typealias Callback<Result> = (Outcome<Result>)->Unit

sealed class Outcome<Result> {
    data class Success<Result>(val result: Result) : Outcome<Result>()
    data class Error<Result>(val error: Throwable) : Outcome<Result>()
}