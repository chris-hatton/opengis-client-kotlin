package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element

/**
 * Specified in: http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd
 *
 * Available HTTP request methods. At least "Get" shall be supported.
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

