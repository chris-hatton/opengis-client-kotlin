package opengis.model.xml.wms

import opengis.model.xml.ogc.FilterCapabilities
import opengis.model.xml.ows.CapabilitiesBaseType
import opengis.model.xml.ows.OperationsMetadata
import opengis.model.xml.ows.ServiceIdentification
import opengis.model.xml.ows.ServiceProvider
import opengis.model.xml.wfs.FeatureTypeList
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
@Root(strict=false, name = "WMS_Capabilities")
class WmsCapabilities(
        @field:Attribute var updateSequence : Int      = 0,
        @field:Attribute var version        : String   = "",
        serviceIdentification : ServiceIdentification? = null,
        serviceProvider       : ServiceProvider?       = null,
        operationsMetadata    : OperationsMetadata?    = null
) : CapabilitiesBaseType(
        serviceIdentification,
        serviceProvider,
        operationsMetadata
)
