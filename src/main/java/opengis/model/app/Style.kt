package opengis.model.app

/**
 * Created by Chris on 10/08/2017.
 */
class Style( val name: String = defaultStyleName) {

    companion object {
        val defaultStyleName = ""
        val default = Style(defaultStyleName)
    }

    override fun toString() : String = name
}