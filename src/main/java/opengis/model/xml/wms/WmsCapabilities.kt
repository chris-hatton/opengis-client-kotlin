package opengis.model.xml.wms

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * A WMS_Capabilities document is returned in response to a GetCapabilities request made on a WMS.
 */
@Root(strict=false, name = "WMS_Capabilities")
class WmsCapabilities(
        //@field:Attribute var updateSequence : Int         = 0,
        //@field:Attribute var version        : String      = "",
        @field:Element   var service        : Service?    = null,
        @field:Element   var capability     : Capability? = null
)