package opengis.model.app

/**
 * Created by Chris on 11/08/2017.
 */
enum class MimeType(val string: String) {
    PNG("image/png"),
    JSON("application/json");

    override fun toString(): String = string
}
