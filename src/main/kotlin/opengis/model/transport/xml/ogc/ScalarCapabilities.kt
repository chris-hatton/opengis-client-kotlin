package opengis.model.transport.xml.ogc

import org.simpleframework.xml.ElementList


data class ScalarCapabilities(
    @field:ElementList(name="LogicalOperators",    entry="LogicalOperator"   ) var logicalOperators    : List<String>? = null,
    @field:ElementList(name="ComparisonOperators", entry="ComparisonOperator") var comparisonOperators : List<String>? = null,
    @field:ElementList(name="ArithmeticOperators", entry="ArithmeticOperator") var arithmeticOperators : List<String>? = null
)