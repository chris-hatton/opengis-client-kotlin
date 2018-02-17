package opengis.model.xml.wfs

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name="WGS84BoundingBox")
data class WGS84BoundingBox(
    @field:Element(name="LowerCorner") var lowerCorner : String? = null,
    @field:Element(name="UpperCorner") var upperCorner : String? = null
)
