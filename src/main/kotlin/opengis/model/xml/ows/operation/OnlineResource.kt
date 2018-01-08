package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

/**
 * An OnlineResource is typically an HTTP URL.
 */
@Root(name="OnlineResource")
data class OnlineResource(
    @field:Attribute(name="href") var url  : String? = null,
    @field:Attribute(name="type", required = false) var type : String? = null // TODO" Required?
)