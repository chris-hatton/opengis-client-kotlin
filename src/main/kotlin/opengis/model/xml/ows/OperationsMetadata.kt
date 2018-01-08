package opengis.model.xml.ows

import opengis.model.xml.ows.operation.Constraint
import opengis.model.xml.ows.operation.Operation
import opengis.model.xml.ows.operation.Parameter
import org.simpleframework.xml.ElementList

/**
 * Metadata about the operations and related abilities specified by this service and implemented by
 * this server, including the URLs for operation requests. The basic contents of this section shall
 * be the same for all OWS types, but individual serviceUrls can add elements and/or change the optionality
 * of optional elements.
 */
data class OperationsMetadata(
    @field:ElementList(entry="Operation",  required = false, inline = true) var operations  : List<Operation>?  = null,
    @field:ElementList(entry="Parameter",  required = false, inline = true) var parameters  : List<Parameter>?  = null,
    @field:ElementList(entry="Constraint", required = false, inline = true) var constraints : List<Constraint>? = null
)
