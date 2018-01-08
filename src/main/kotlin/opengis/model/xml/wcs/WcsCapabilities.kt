package opengis.model.xml.wcs

import opengis.model.xml.ows.CapabilitiesBaseType
import opengis.model.xml.ows.OperationsMetadata
import opengis.model.xml.ows.ServiceIdentification
import opengis.model.xml.ows.ServiceProvider
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