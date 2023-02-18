package extensions

import java.util.*

fun String.romanToInt(): Int {
    val romanCharset = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)

    this.uppercase(Locale.getDefault()).forEach {
        if (!romanCharset.contains(it)) {
            throw Exception("The string has characters not related to the Roman notation")
        }
    }

    for (i in 0 until this.length - 1) {
        
    }
    return 0
}