package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList


data class Operation(
        @field:Attribute  ( name="name", required = false) var name        : String?           = null,
        @field:Element    ( name="DCP"        )            var DCPType: DCPType?              = null,
        @field:ElementList( entry="Parameter" , inline = true, required = false ) var parameters  : List<Parameter>?  = null,
        @field:ElementList( entry="Constraint", inline = true, required = false ) var constraints : List<Constraint>? = null
)
