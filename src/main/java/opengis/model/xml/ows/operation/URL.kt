package opengis.model.xml.ows.operation

import org.simpleframework.xml.Element

/**
 * De-duplication of the common '*URL' element-type found in OpenGIS specifications.
 */
class URL(
    @field:Element(name="Format",         required = true) var format         : String?         = null,
    @field:Element(name="OnlineResource", required = true) var onlineResource : OnlineResource? = null
)