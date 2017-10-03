package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:ContactInfo>
        <ows:Phone>
          ...
        </ows:Phone>
        <ows:Address>
          ...
        </ows:Address>
      </ows:ContactInfo>
 */
data class ContactInfo(
    @Element val phone   : Phone,
    @Element val address : Address
)