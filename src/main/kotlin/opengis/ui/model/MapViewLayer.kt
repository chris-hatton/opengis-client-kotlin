package opengis.ui.model

import opengis.model.app.request.OpenGisRequest
import opengis.model.app.request.wmts.Layer
import opengis.model.transport.xml.wms.WmsCapabilities
import opengis.ui.TileProvider
import opengis.ui.WmtsTileProvider
import opengis.ui.view.MapViewContract

/**
 * Created by Chris on 04/02/2018.
 */
sealed class MapViewLayer {

    abstract class Tile<T: OpenGisRequest<ByteArray>>(protected val tileProvider:TileProvider<T>) : MapViewLayer()  {

        class Wmts(layer: Layer) : Tile(WmtsTileProvider()) {
            override fun addTo(mapView: MapViewContract) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        }
        abstract class Wms(layer: WmsCapabilities.Capability.Layer) : Tile() {

        }
    }

    class Feature : MapViewLayer() {
        override fun addTo(mapView: MapViewContract) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    abstract fun addTo( mapView: MapViewContract);
}