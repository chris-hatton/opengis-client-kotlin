
package opengis.process.projection

import opengis.process.projection.Point

sealed class Tile(val x:Int, val y: Int, val zoom: Int) {
    class TMS(x:Int, y: Int, zoom: Int) : Tile(x,y,zoom) {

        /** Returns bounds of the given tile */
        fun toDegreeBounds(projection: Projection) : Bounds<Point.Degrees> = when(projection) {
            Projection.GlobalGeodetic -> toGeodeticDegreeBounds()
            Projection.GlobalMercator -> toMercatorDegreeBounds()
        }

        private fun toGeodeticDegreeBounds() : Bounds<Point.Degrees> {
            val res : Double = Point.geodeticResolution(zoom)

            val southWest = Point.Degrees(
                    longitude = ( x   * 256 * res - 180),
                    latitude  = ( y   * 256 * res - 90 )
            )

            val northEast = Point.Degrees(
                    longitude = (x+1) * 256 * res - 180,
                    latitude  = (y+1) * 256 * res - 90
            )

            return Bounds( southWest, northEast )
        }

        private fun toMercatorDegreeBounds() : Bounds<Point.Degrees> = toMercatorMeterBounds().toDegrees()

        /** Returns bounds of the given tile in EPSG:900913 coordinates */
        fun toMercatorMeterBounds() : Bounds<Point.Meters> = Bounds(
                southWest = Point.Pixels( x      * Point.tileSize,  y      * Point.tileSize, zoom ),
                northEast = Point.Pixels((x + 1) * Point.tileSize, (y + 1) * Point.tileSize, zoom )
        ).toMeters()

        /**
         * Converts TMS tile coordinates to Google Tile coordinates
         * coordinate origin is moved from bottom-left to top-left corner of the extent
         */
        fun toGoogle() : Google = Google(x, flippedY(), zoom)

        /** "Converts TMS tile coordinates to Microsoft QuadTree" */
        fun toMicrosoftQuadTree() : String {
            var quadKey = ""
            val ty : Int = (Math.pow(2.0,zoom.toDouble()).toInt() - 1) - y
            for( i in zoom downTo 0) {
                var digit = 0
                val mask : Int = 1 shl (i - 1)
                if( (x and mask) != 0) {
                    digit += 1
                }
                if( (ty and mask) != 0 ) {
                    digit += 2
                }
                quadKey += digit.toString()
            }

            return quadKey
        }
    }

    class Google(x:Int, y: Int, zoom: Int) : Tile(x,y,zoom) {

        /** Returns bounds of this Google Tile in latitude/longitude using WGS84 datum */
        fun toBounds(projection: Projection) : Bounds<Point.Degrees> = toTms().toDegreeBounds(projection)

        /** Converts Google Tile coordinates to TMS tile coordinates. */
        fun toTms() : TMS = TMS(x, flippedY(), zoom)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    protected fun flippedY() = (Math.pow(2.0, zoom.toDouble()).toInt() - 1) - y

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tile) return false

        if (x != other.x) return false
        if (y != other.y) return false
        if (zoom != other.zoom) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + zoom
        return result
    }
}