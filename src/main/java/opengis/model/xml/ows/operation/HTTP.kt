package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

/**
 * Created by Chris on 03/10/2017.
 */
data class HTTP(
    @field:Element(name="Get",  required = false) var get : Operation.Get?  = null,
    @field:Element(name="Post", required = false) var post: Operation.Post? = null
) {
    sealed class Operation(@field:Attribute(name = "href") var url:String?) {
        class Get (url: String? = null) : Operation(url)
        class Post(url: String? = null) : Operation(url)
    }
}

