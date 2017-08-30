package opengis.model

/**
 * Created by Chris on 10/08/2017.
 */
class Style( val name: String = "" ) {

    companion object {
        val default = Style("")
    }

    override fun toString() : String = name
}