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
    @field:Element(name="Phone"  ) var phone   : Phone?   = null,
    @field:Element(name="Address") var address : Address? = null
)