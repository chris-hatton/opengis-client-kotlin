package opengis.model.xml.ows

import opengis.model.xml.ows.operation.Operation
import org.simpleframework.xml.ElementList


data class OperationsMetadata(
    @field:ElementList(entry="Operation", required = false, inline = true) var operations: List<Operation>? = null
)
