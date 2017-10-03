package opengis.model.xml.ows

import opengis.model.xml.ows.operation.Operation

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
data class OperationsMetaData(
    val operations: List<Operation>
)