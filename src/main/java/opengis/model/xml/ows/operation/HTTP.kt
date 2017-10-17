package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path

/**
 * Specified in: http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd
 *
 * Available HTTP request methods. At least "Get" shall be supported.
 */
data class HTTP(
    @field:Element(name="Get",  required = true ) var get : Operation.Get?  = null,
    @field:Element(name="Post", required = false) var post: Operation.Post? = null
) {
    sealed class Operation(
        @field:Element(name="href") var onlineResource: OnlineResource? = null
    ) {
        class Get  : Operation()
        class Post : Operation()
    }
}

