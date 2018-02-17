package opengis.process.projection

import geojson.BoundingBox
import opengis.process.projection.Point

data class Bounds<out P: Point<*>>(val southWest: P, val northEast: P ) {

    fun toMeters()          : Bounds<Point.Meters>  = Bounds( southWest.toMeters(),     northEast.toMeters()     )
    fun toDegrees()         : Bounds<Point.Degrees> = Bounds( southWest.toDegrees(),    northEast.toDegrees()    )
    fun toPixels(zoom: Int) : Bounds<Point.Pixels>  = Bounds( southWest.toPixels(zoom), northEast.toPixels(zoom) )
    fun toRaster(zoom: Int) : Bounds<Point.Raster>  = Bounds( southWest.toRaster(zoom), northEast.toRaster(zoom) )

    fun toGeoJsonBoundingBox() = BoundingBox(
            southWest = this.southWest.toDegrees().toGeoJsonPoint(),
            northEast = this.northEast.toDegrees().toGeoJsonPoint()
    )
}
