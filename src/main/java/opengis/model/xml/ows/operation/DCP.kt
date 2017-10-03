package opengis.model.xml.ows.operation

import org.simpleframework.xml.Element


data class DCP(
    @field:Element(name="HTTP") var httpUrls: HTTP? = null
)