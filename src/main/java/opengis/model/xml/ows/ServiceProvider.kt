package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:ServiceProvider>
    <ows:ProviderName>DELWP</ows:ProviderName>
    <ows:ServiceContact>
      ...
    </ows:ServiceContact>
  </ows:ServiceProvider>
 */
class ServiceProvider(
    @field:Element(name="ProviderName"  ) var providerName   : String?         = null,
    @field:Element(name="ServiceContact") var serviceContact : ServiceContact? = null
)