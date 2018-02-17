package opengis.model.transport.xml.ows

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList


data class ServiceIdentification(
    @field:Element    (name="Title"             ) var title              : String?       = null,
    @field:Element    (name="Abstract"          ) var abstract           : String?       = null,
    @field:ElementList(name="Keywords"          ) var keywords           : List<String>? = null,
    @field:Element    (name="ServiceType"       ) var serviceType        : String?       = null,
    @field:Element    (name="ServiceTypeVersion") var serviceTypeVersion : String?       = null,
    @field:Element    (name="Fees"              ) var fees               : String?       = null,
    @field:Element    (name="AccessConstraints" ) var accessConstraints  : String?       = null
)