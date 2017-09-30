package opengis.test

import opengis.process.projection.Bounds
import opengis.process.projection.Point
import opengis.process.projection.Projection
import opengis.process.projection.Tile
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Source of truth: http://www.maptiler.org/google-maps-coordinates-tile-bounds-projection/
 * Web-page calculates (or at least formats) results to 8 d.p.
 */
class TestProjection {

    companion object {
        val testPrecision = 8 // Decimal places
    }

    @Test
    fun testPointDegreesToMeters() {
        run {
            val degrees        = Point.Degrees( longitude = -135.0, latitude = 66.51326044311185 )
            val expectedMeters = Point.Meters( x = -15028131.257091932, y = 10018754.17139462 )
            val actualMeters = degrees.toMeters()
            actualMeters.assertApproxEquals(expectedMeters)
        }

        run {
            val degrees        = Point.Degrees( longitude = 135.0, latitude = -79.17133464081944 )
            val expectedMeters = Point.Meters( x = 15028131.257091936, y = -15028131.257091932 )
            val actualMeters = degrees.toMeters()
            actualMeters.assertApproxEquals(expectedMeters)
        }
    }

    @Test
    fun testTmsTileToGoogle() {
        run {
            val tmsTile = Tile.TMS( x = 9, y = 12, zoom = 4 )
            val expectedGoogle = Tile.Google( x = 9, y = 3, zoom = 4 )
            val actualGoogle = tmsTile.toGoogle()
            assertEquals(expectedGoogle,actualGoogle)
        }
    }

    @Test
    fun testGoogleTileToTms() {
        run {
            val googleTile  = Tile.Google( x = 231, y = 157, zoom = 8 )
            val expectedTms = Tile.TMS   ( x = 231, y =  98, zoom = 8 )
            val actualTms = googleTile.toTms()
            assertEquals(expectedTms,actualTms)
        }
    }

    @Test
    fun testTmsTileToMeterBounds() {
        run {
            val tmsTile = Tile.TMS(x = 9, y = 12, zoom = 4)
            val expectedMercatorBounds = Bounds(
                    southWest = Point.Meters(x = 2504688.542848654, y = 10018754.17139462),
                    northEast = Point.Meters(x = 5009377.085697312, y = 12523442.714243278)
            )
            val actualMercatorBounds = tmsTile.toMercatorMeterBounds()
            actualMercatorBounds.assertApproxEquals(expectedMercatorBounds)
        }
    }

    @Test
    fun testMercatorTmsTileToDegreeBounds() {

        run {
            val zoom = 1
            val tmsTile = Tile.TMS( x = 0, y = 1, zoom = zoom )
            val expectedDegreeBounds = Bounds(
                southWest = Point.Degrees( longitude = -180.0, latitude =  0.0              ),
                northEast = Point.Degrees( longitude =    0.0, latitude = 85.05112877980659 )
            )
            val actualDegreeBounds : Bounds<Point.Degrees> = tmsTile.toDegreeBounds( projection = Projection.GlobalMercator )
            actualDegreeBounds.assertApproxEquals(expectedDegreeBounds)
        }

        run {
            val zoom = 4
            val tmsTile = Tile.TMS( x = 10, y = 11, zoom = zoom )
            val expectedDegreeBounds = Bounds(
                southWest = Point.Degrees( longitude = 45.0, latitude = 55.7765730186677 ),
                northEast = Point.Degrees( longitude = 67.5, latitude = 66.51326044311185 )
            )
            val actualDegreeBounds : Bounds<Point.Degrees> = tmsTile.toDegreeBounds( projection = Projection.GlobalMercator )
            actualDegreeBounds.assertApproxEquals(expectedDegreeBounds)
        }
    }

    private fun Point.Meters.assertApproxEquals(expected: Point.Meters, decimalPlaces: Int = testPrecision  ) {
        val delta : Double = Math.pow(10.0,-decimalPlaces.toDouble())
        Assert.assertEquals( expected.x, x, delta )
        Assert.assertEquals( expected.y, y, delta )
    }

    private fun <P:Point<Double>> P.assertApproxEquals(expected: P, decimalPlaces: Int = testPrecision  ) {
        val delta : Double = Math.pow(10.0,-decimalPlaces.toDouble())
        Assert.assertEquals( expected.x, x, delta )
        Assert.assertEquals( expected.y, y, delta )
    }

    private fun Point.Pixels.assertApproxEquals(expected: Point.Pixels) {
        Assert.assertEquals( expected.x, x )
        Assert.assertEquals( expected.y, y )
    }

    private fun <P:Point<Double>> Bounds<P>.assertApproxEquals(expected: Bounds<P>, decimalPlaces: Int = testPrecision) {
        this.southWest.assertApproxEquals(expected.southWest,decimalPlaces)
        this.northEast.assertApproxEquals(expected.northEast,decimalPlaces)
    }

    private fun Bounds<Point.Pixels>.assertApproxEquals(expected: Bounds<Point.Pixels>) {
        this.southWest.assertApproxEquals(expected.southWest)
        this.northEast.assertApproxEquals(expected.northEast)
    }
}

