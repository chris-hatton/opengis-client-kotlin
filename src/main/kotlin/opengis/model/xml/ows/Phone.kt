package opengis.model.xml.ows

import org.simpleframework.xml.Element


data class Phone(
    @field:Element(name="Voice"                      ) var voice     : String? = null,
    @field:Element(name="Facsimile", required = false) var facsimile : String? = null
)