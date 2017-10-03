package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 03/10/2017.
 */
data class Constraint(
    @field:Attribute(name="name") var name : String? = null,
    @field:ElementList(entry="Value",inline=true) var values : List<String>? = null
)