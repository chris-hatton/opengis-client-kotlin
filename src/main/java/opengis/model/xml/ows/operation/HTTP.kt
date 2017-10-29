package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path

/**
 * Specified in: http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd
 *
 * Available HTTP request methods. At least "Get" shall be supported.
 *
 * @param get The URL prefix for the HTTP "Get" request method.
 * @param post The URL prefix for the HTTP "Post" request method.
 */
data class HTTP(
    @field:Path("Get" ) @field:Element(name="OnlineResource", required = true ) var get : OnlineResource? = null,
    @field:Path("Post") @field:Element(name="OnlineResource", required = false) var post: OnlineResource? = null
)