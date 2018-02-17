package opengis.ui.view

import geojson.Feature
import geojson.FeatureCollection
import opengis.ui.FeatureProvider
import opengis.ui.TileProvider

/**
 * Created by Chris on 19/01/2018.
 */
interface MapViewContract {
    fun addTileLayer( provider: TileProvider<*> )
    fun removeTileLayer( provider: TileProvider<*> )
    fun addFeatures( provider: Iterable<Feature> )
}