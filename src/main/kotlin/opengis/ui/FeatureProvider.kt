package opengis.ui

import geojson.Feature
import opengis.process.Callback

/**
 * Created by Chris on 23/01/2018.
 */
interface FeatureProvider {
    fun getFeatures() : Callback<Iterable<Feature>>
}