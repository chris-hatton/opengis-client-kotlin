package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

/**
 * Specified in: http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd
 *
 * Available HTTP request methods. At least "Get" shall be supported.
 *
 * @param get The URL prefix for the HTTP "Get" request method.
 * @param post The URL prefix for the HTTP "Post" request method.
 */
@Root(name="HTTP")
data class HTTP(
        // OWS v1.1
        @field:Path("Get" ) @field:Element  (name="OnlineResource", required = false) var getResource  : OnlineResource? = null,

        // OWS v1.0
        @field:Path("Get" ) @field:Attribute(name="href",           required = false) var getHref      : String?         = null,

        // OWS v1.1
        @field:Path("Post") @field:Element  (name="OnlineResource", required = false) var postResource : OnlineResource? = null,

        // OWS v1.0
        @field:Path("Post") @field:Attribute(name="href",           required = false) var postHref     : String?         = null
) {
    val getUrl  : String? = getResource ?.url ?: getHref
    val postUrl : String? = postResource?.url ?: postHref
}