package opengis.model.transport.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList


data class Constraint(
    @field:Attribute(name="name") var name : String? = null,
    @field:ElementList(entry="Value",inline=true) var values : List<String>? = null
)