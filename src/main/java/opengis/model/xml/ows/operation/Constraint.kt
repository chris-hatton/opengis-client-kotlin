package opengis.model.xml.ows.operation

/**
 * Created by Chris on 02/10/2017.
 */
/*
    <ows:Constraint name="LocalTraverseXLinkScope">
        <ows:Value>2</ows:Value>
    </ows:Constraint>
 */
data class Constraint(
    val name  : String,
    val value : String
)