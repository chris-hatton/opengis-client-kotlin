package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
open class CapabilitiesBaseType(
    @field:Element var serviceIdentification : ServiceIdentification? = null,
    @field:Element var serviceProvider       : ServiceProvider?       = null,
    @field:Element var operationsMetaData    : OperationsMetaData?    = null
) {
    constructor() : this(null,null,null)
}