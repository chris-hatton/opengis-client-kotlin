package opengis.model.transport.xml.wfs

import opengis.model.transport.xml.ogc.FilterCapabilities
import opengis.model.transport.xml.ows.CapabilitiesBaseType
import opengis.model.transport.xml.ows.OperationsMetadata
import opengis.model.transport.xml.ows.ServiceIdentification
import opengis.model.transport.xml.ows.ServiceProvider
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
@Root(name = "WFS_Capabilities", strict = false)
class WfsCapabilities(
        @field:Attribute var updateSequence : Int      = 0,
        @field:Attribute var version        : String   = "",
        serviceIdentification : ServiceIdentification? = null,
        serviceProvider       : ServiceProvider?       = null,
        operationsMetadata    : OperationsMetadata?    = null,
        @field:Element  (name = "FeatureTypeList"    ) var featureTypeList    : FeatureTypeList?    = null,
        @field:Element  (name = "Filter_Capabilities") var filterCapabilities : FilterCapabilities? = null,
        //@field:Attribute(name = "schemaLocation" ) var schemaLocation     : String?             = null,
        @field:ElementList(name = "ServesGMLObjectTypeList", required = false  ) var servesGmlObjectTypeList : List<GMLObjectType>? = null,
        @field:ElementList(name = "SupportsGMLObjectTypeList", required = false ) var supportsGmlObjectTypeList : List<GMLObjectType>? = null
) : CapabilitiesBaseType(
    serviceIdentification,
    serviceProvider,
    operationsMetadata
)
