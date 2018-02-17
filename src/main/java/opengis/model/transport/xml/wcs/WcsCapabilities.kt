package opengis.model.transport.xml.wcs

import opengis.model.transport.xml.ows.CapabilitiesBaseType
import opengis.model.transport.xml.ows.OperationsMetadata
import opengis.model.transport.xml.ows.ServiceIdentification
import opengis.model.transport.xml.ows.ServiceProvider
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict=false, name = "WCS_Capabilities")
class WcsCapabilities(
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