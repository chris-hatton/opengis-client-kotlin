package opengis.model.app

import java.util.*

/**
 * Created by Chris on 10/08/2017.
 */
sealed class UpdateSequence {
    data class Integer( val value: Int ) : UpdateSequence()
    data class Timestamp( val date: Date) : UpdateSequence()
}