package opengis.model.transport.xml.wfs

import opengis.model.transport.xml.wfs.FeatureType
import org.simpleframework.xml.ElementList


data class FeatureTypeList(
        @field:ElementList(name="Operations", entry="Operation", required=false, empty=true) var operations   : List<String>?      = null,
        @field:ElementList(entry="FeatureType", inline=true)     var featureTypes : List<FeatureType>? = null
)