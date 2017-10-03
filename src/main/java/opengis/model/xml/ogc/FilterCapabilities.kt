package opengis.model.xml.ogc

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(strict=false)
data class FilterCapabilities(
    @field:Element(name="Spatial_Capabilities") var spatial : SpatialCapabilities? = null,
    @field:Element(name="Scalar_Capabilities" ) var scalar  : ScalarCapabilities?  = null
    //@field:Element(name="Id_Capabilities" ) var scalar  : ScalarCapabilities?  = null
)