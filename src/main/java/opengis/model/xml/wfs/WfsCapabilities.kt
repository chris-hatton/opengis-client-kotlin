package opengis.model.xml.wfs

import opengis.model.UpdateSequence
import opengis.model.xml.ows.CapabilitiesBaseType
import opengis.model.xml.ows.OperationsMetaData
import opengis.model.xml.ows.ServiceIdentification
import opengis.model.xml.ows.ServiceProvider
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

/**
 * Model for the Service Meta-data document generated in response to OpenGIS 'GetCapabilties'
 * requests.
 */
@Root()
class WfsCapabilities(
    @field:Attribute var updateSequence: Int,
    @field:Attribute var version : String,
    serviceIdentification : ServiceIdentification?,
    serviceProvider       : ServiceProvider?,
    operationsMetaData    : OperationsMetaData?
) : CapabilitiesBaseType(
    serviceIdentification,
    serviceProvider,
    operationsMetaData
) {
    constructor() : this(0,"1.0.0",null,null,null)
}
