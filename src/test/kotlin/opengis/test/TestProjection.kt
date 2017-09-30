package opengis.test

import opengis.process.projection.Bounds
import opengis.process.projection.Point
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
    fun testTmsTileToMercatorBounds() {
        run {
            val tmsTile = Tile.TMS( x = 9, y = 12, zoom = 4 )
            val expectedMercatorBounds = Bounds(
                southWest = Point.Meters( x = 2504688.542848654, y = 10018754.17139462),
                northEast = Point.Meters( x = 5009377.085697312, y = 12523442.714243278)
            )
            val actualMercatorBounds = tmsTile.toMeterBounds()
            actualMercatorBounds.assertApproxEquals(expectedMercatorBounds)
        }
    }

    private fun Point.Meters.assertApproxEquals(other: Point.Meters, decimalPlaces: Int = testPrecision  ) {
        val delta : Double = Math.pow(10.0,-decimalPlaces.toDouble())
        Assert.assertEquals( x, other.x, delta )
        Assert.assertEquals( y, other.y, delta )
    }

    private fun Bounds<Point.Meters>.assertApproxEquals(other: Bounds<Point.Meters>, decimalPlaces: Int = testPrecision) {
        this.southWest.assertApproxEquals(other.southWest,decimalPlaces)
        this.northEast.assertApproxEquals(other.northEast,decimalPlaces)
    }
}

