package opengis.model

/**
 * Created by Chris on 11/08/2017.
 */
enum class MimeType(val string: String) {
    PNG("image/png");

    override fun toString(): String = string
}
