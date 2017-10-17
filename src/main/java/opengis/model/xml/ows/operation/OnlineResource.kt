package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name="OnlineResource")
data class OnlineResource(
    @field:Attribute(name="href") var url  : String? = null,
    @field:Attribute(name="type") var type : String? = null
)