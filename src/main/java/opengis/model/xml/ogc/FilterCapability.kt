package opengis.model.xml.ogc

import java.util.logging.Filter

/**
 * Created by Chris on 03/10/2017.
 */
sealed class FilterCapability {
    sealed class Spatial : FilterCapability() {
        data class GeometryOperand(val name: String) : Spatial()
        data class Operator       (val name: String) : Spatial()
    }

    class Scalar  : FilterCapability() {
        //data class LogicalOperator
    }
    class Id      : FilterCapability()
}