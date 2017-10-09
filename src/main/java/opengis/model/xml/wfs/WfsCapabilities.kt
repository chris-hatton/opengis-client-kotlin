package opengis.model.xml.wfs

import opengis.model.xml.ogc.FilterCapabilities
import opengis.model.xml.ows.CapabilitiesBaseType
import opengis.model.xml.ows.OperationsMetadata
import opengis.model.xml.ows.ServiceIdentification
import opengis.model.xml.ows.ServiceProvider
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
@Root(strict=false, name = "WFS_Capabilities")
class WfsCapabilities(
        @field:Attribute var updateSequence : Int      = 0,
        @field:Attribute var version        : String   = "",
        serviceIdentification : ServiceIdentification? = null,
        serviceProvider       : ServiceProvider?       = null,
        operationsMetadata    : OperationsMetadata?    = null,
        @field:Element(name = "FeatureTypeList"    ) var featureTypeList    : FeatureTypeList?    = null,
        @field:Element(name = "Filter_Capabilities") var filterCapabilities : FilterCapabilities? = null
) : CapabilitiesBaseType(
    serviceIdentification,
    serviceProvider,
    operationsMetadata
)
