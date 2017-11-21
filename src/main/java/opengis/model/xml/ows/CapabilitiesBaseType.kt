package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
open class CapabilitiesBaseType(
        @field:Element(name="ServiceIdentification") var serviceIdentification : ServiceIdentification? = null,
        @field:Element(name="ServiceProvider"      ) var serviceProvider       : ServiceProvider?       = null,
        @field:Element(name="OperationsMetadata"   ) var operationsMetadata    : OperationsMetadata?    = null
)
