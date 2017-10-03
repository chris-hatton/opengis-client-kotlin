package opengis.model.xml.ows

import opengis.model.xml.ows.operation.Operation
import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 02/10/2017.
 */
/*
  <ows:OperationsMetadata>
    <ows:Operation name="GetFeature">
        ...
    </ows:Operation>
  </ows:OperationsMetadata>
 */
data class OperationsMetadata(
    @field:ElementList(entry="Operation", required = false, inline = true) var operations: List<Operation>? = null
)
