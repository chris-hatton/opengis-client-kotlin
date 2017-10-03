package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:ServiceContact>
      <ows:IndividualName>Service Center</ows:IndividualName>
      <ows:PositionName>Web Data Services</ows:PositionName>
      <ows:ContactInfo>
        ...
      </ows:ContactInfo>
    </ows:ServiceContact>
 */
data class ServiceContact(
    @field:Element(name="IndividualName") var individualName : String?      = null,
    @field:Element(name="PositionName"  ) var positionName   : String?      = null,
    @field:Element(name="ContactInfo"   ) var contactInfo    : ContactInfo? = null
)