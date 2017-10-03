package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


data class Parameter(
    @field:Attribute(name="name") var name : String? = null,
    @field:ElementList(entry="Value",inline=true) var values : List<String>? = null
)