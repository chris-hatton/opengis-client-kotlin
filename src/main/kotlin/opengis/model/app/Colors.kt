package opengis.model.app

data class Color( val red: Int, val green: Int, val blue: Int, val alpha: Int = 255 )

fun Color.toHexString() : String = String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
