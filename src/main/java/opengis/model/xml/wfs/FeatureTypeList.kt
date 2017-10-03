package opengis.model.xml.wfs

import org.simpleframework.xml.ElementList


data class FeatureTypeList(
        @field:ElementList(name="Operations", entry ="Operation") var operations   : List<String>?      = null,
        @field:ElementList(entry ="FeatureType", inline=true)     var featureTypes : List<FeatureType>? = null
)