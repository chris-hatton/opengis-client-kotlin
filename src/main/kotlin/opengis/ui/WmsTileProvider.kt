package opengis.ui

import geojson.BoundingBox
import opengis.model.app.CRS
import opengis.model.app.request.wmts.Layer
import opengis.model.app.Style
import opengis.model.app.request.wms.GetMap
import opengis.process.OpenGisHttpClient
import opengis.process.projection.Projection
import opengis.process.projection.Tile

class WmsTileProvider(
        client           : OpenGisHttpClient,
        baseRequest      : GetMap<ByteArray>
): TileProvider<GetMap<ByteArray>>( client, baseRequest ) {

    constructor(
            client    : OpenGisHttpClient,
            layerName : String
    ) : this (
            client       = client,
            styledLayers = listOf(GetMap.StyledLayer(Layer(layerName), Style.default))
    )

    constructor(
            client       : OpenGisHttpClient,
            styledLayers : List<GetMap.StyledLayer>
    ) : this(
            client      = client,
            baseRequest = WmsTileProvider.buildBaseTileRequest(styledLayers)
    )

    companion object {

        fun buildBaseTileRequest(
                styledLayers : List<GetMap.StyledLayer>
        ) : GetMap<ByteArray> = GetMap(
                styledLayers = styledLayers,
                reference    = CRS.Layer( nameSpace = CRS.Namespace.EPSG.name, name = "900913"), // "4326" //"3857"),
                transparent  = true,
                boundingBox  = BoundingBox.Global  // Will be replaced in [getTileRequest]
        )
    }

    override fun getTileRequest(baseRequest: GetMap<ByteArray>, tile: Tile.Google) : GetMap<ByteArray> = baseRequest.copy(
            boundingBox = tile
                    .toBounds( projection = Projection.GlobalMercator )
                    .toGeoJsonBoundingBox()
    )
}
