package opengis.model.xml.ogc

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Chris on 03/10/2017.
 */
@Root(strict=false)
data class FilterCapabilities(
        @field:Element(name="Spatial_Capabilities") var spatial : SpatialCapabilities? = null,
        @field:Element(name="Scalar_Capabilities" ) var scalar  : ScalarCapabilities?  = null
    //@field:Element(name="Id_Capabilities" ) var scalar  : ScalarCapabilities?  = null
)