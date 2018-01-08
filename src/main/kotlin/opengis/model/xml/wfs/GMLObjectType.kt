package opengis.model.xml.wfs

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Name of this GML object type, including any namespace prefix.
 *
 * An element of this type that describes a GML object in an
 * application namespace shall have an xml xmlns specifier,
 * e.g. xmlns:bo="http://www.BlueOx.org/BlueOx"
 */
@Root(name = "GMLObjectType")
class GMLObjectType(
    @field:Element(name="Name"    ) var name     : String? = null,
    @field:Element(name="Title", required = false ) var title    : String? = null,
    @field:Element(name="Abstract", required = false ) var abstract : String? = null,
    @field:ElementList(name="Keywords",entry="Keyword",empty=true, required = false ) var keywords : List<String>? = null,
    @field:ElementList(name="OutputFormats",entry="Format",empty=true, required = false ) var outputFormats : List<String>? = null

)

