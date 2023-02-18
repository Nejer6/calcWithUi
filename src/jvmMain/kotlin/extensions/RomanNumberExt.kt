package extensions

fun String.romanToInt(): Int {
    val romanCharset = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    val str = this.uppercase()

    str.forEach {
        if (!romanCharset.contains(it)) {
            throw Exception("The string has characters not related to the Roman notation")
        }
    }

    var number = 0
    for (i in indices) {
        if (i + 1 < str.length && romanCharset[str[i]]!! < romanCharset[str[i + 1]]!!) {
            number -= romanCharset[str[i]]!!
        } else {
            number += romanCharset[str[i]]!!
        }
    }

    return number
}
