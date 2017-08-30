package geoklient.model.request.wms

import geojson.BoundingBox
import geoklient.model.*

/**
 * Ref: Section 7.3 of OpenGIS Web Map Service WMS Implementation Specification
 *
 * The GetMap operation returns a map.  Upon receiving a GetMap request,
 * a WMS shall either satisfy the request or issue a service exception.
 *
 * @param layers One or more map layers.
 * @param styles One rendering style per requested layer.
 */
class GetMap(
        val styledLayers    : List<StyledLayer>,
        val reference       : CRS,
        val boundingBox     : BoundingBox,
        val width           : Int = defaultTileSize,
        val height          : Int = defaultTileSize,
        val format          : MimeType = MimeType.PNG,
        val transparent     : Boolean?                               = null,
        val backgroundColor : Color?                                 = null,
        val exceptionFormat : ExceptionFormat?                       = null,
        val time            : Time?                                  = null,
        val elevation       : Elevation?                             = null,
        val dimensions      : kotlin.collections.Map<String,String>? = null
) : WebMapServiceRequest() {

    data class StyledLayer(val layer: Layer, val style: Style = Style.default )

    companion object {
        val defaultTileSize : Int = 256
    }

    override val requestIdentifier: String = "GetMap"

    override fun collateParameters(parameters: MutableList<Pair<String, String>>) {
        super.collateParameters(parameters)
        collateMapParameters(parameters)
    }

    /**
     * The main parameter collation for GetMap is split-out from [collateParameters] because
     * the parameters at this level are also required for access by [GetFeatureInfo].
     */
    internal fun collateMapParameters(parameters: MutableList<Pair<String, String>>) {
        parameters.apply {

            // Mandatory parameters
            add( "LAYERS"    to styledLayers.map { it.layer }.joinToString(",") )
            add( "STYLES"    to styledLayers.map { it.style }.joinToString(",") )
            add( "CRS"       to reference  .toString() )
            add( "BBOX"      to boundingBox.toString() )
            add( "WIDTH"     to width      .toString() )
            add( "HEIGHT"    to height     .toString() )
            add( "FORMAT"    to format     .toString() )

            // Optional parameters
            transparent    ?.let { add("TRANSPARENT"      to it.toString()) }
            backgroundColor?.let { add("BGCOLOR"          to it.toHexString()) }
            exceptionFormat?.let { add("EXCEPTION_FORMAT" to it.toString()) }
            time           ?.let { add("TIME"             to it.toString()) }
            elevation      ?.let { add("ELEVATION"        to it.toString()) }
            dimensions     ?.let { add("DIMENSIONS"       to it.toString()) }
        }

    }
}