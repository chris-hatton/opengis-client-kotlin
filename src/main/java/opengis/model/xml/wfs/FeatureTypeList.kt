package opengis.model.xml.wfs

import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 03/10/2017.
 */
data class FeatureTypeList(
        @field:ElementList(name="Operations", entry ="Operation") var operations   : List<String>?      = null,
        @field:ElementList(entry ="FeatureType", inline=true)     var featureTypes : List<FeatureType>? = null
)