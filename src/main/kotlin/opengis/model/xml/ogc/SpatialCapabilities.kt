package opengis.model.xml.ogc

import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 03/10/2017.
 */
/**
 * Created by Chris on 03/10/2017.
 */
data class SpatialCapabilities(
        @field:ElementList(name="GeometryOperands", entry="GeometryOperand") var geometryOperands : List<String>? = null,
        @field:ElementList(name="SpatialOperators", entry="SpatialOperator") var spatialOperators : List<String>? = null
)