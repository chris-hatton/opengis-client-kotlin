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
    @Element val providerName   : String,
    @Element val serviceContact : ServiceContact
)