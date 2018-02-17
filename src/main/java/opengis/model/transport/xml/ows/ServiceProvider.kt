package opengis.model.transport.xml.ows

import opengis.model.transport.xml.ows.ServiceContact
import opengis.model.transport.xml.ows.operation.OnlineResource
import org.simpleframework.xml.Element

/**
 * TBC
 * @param providerName A unique identifier for the service provider organization.
 * @param providerSite Reference to the most relevant web site of the service provider.
 * @param serviceContact Information for contacting the service provider. The OnlineResource element within this ServiceContact element should not be used to reference a web site of the service provider.
 */
class ServiceProvider(
        @field:Element(name="ProviderName",   required = true  ) var providerName   : String?         = null,
        @field:Element(name="ProviderSite",   required = false ) var providerSite   : OnlineResource? = null,
        @field:Element(name="ServiceContact", required = true  ) var serviceContact : ServiceContact? = null  // ResponsiblePartySubsetType?
)