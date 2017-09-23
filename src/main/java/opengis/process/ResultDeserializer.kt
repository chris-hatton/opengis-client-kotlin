package opengis.process

import kimage.model.Image
import kimage.model.KImage
import kimage.model.pixel.RGB


typealias ResultDeserializer<Result> = (ByteArray)->Result

val imageAdapter : ResultDeserializer<Image<RGB>> = { bytes: ByteArray ->
    KImage(width = 2, height = 2, init = { RGB.black } )
}
