package opengis.model.xml.ogc

import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 03/10/2017.
 */
data class ScalarCapabilities(
        @field:ElementList(name="LogicalOperators",    entry="LogicalOperator"   ) var logicalOperators    : List<String>? = null,
        @field:ElementList(name="ComparisonOperators", entry="ComparisonOperator") var comparisonOperators : List<String>? = null,
        @field:ElementList(name="ArithmeticOperators", entry="ArithmeticOperator") var arithmeticOperators : List<String>? = null
)