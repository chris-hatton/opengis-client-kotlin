package opengis.model.xml.wms

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
@Root(strict=false, name = "WMS_Capabilities")
class WmsCapabilities(
        @field:Attribute var updateSequence : Int         = 0,
        @field:Attribute var version        : String      = "",
        @field:Element   var service        : Service?    = null,
        @field:Element   var capability     : Capability? = null
)