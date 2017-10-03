package opengis.model.xml.ows

import org.simpleframework.xml.Element


class ServiceProvider(
    @field:Element(name="ProviderName"  ) var providerName   : String?         = null,
    @field:Element(name="ServiceContact") var serviceContact : ServiceContact? = null
)