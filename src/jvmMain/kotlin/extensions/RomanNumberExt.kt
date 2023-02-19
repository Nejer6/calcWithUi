package extensions

import kotlin.math.pow

const val MAX_ROMAN_NUMBER = 3999
val romanCharset = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
val numbersByRuleOfSubtraction = mapOf("IV" to 4, "IX" to 9, "XL" to 40, "XC" to 90, "CD" to 400, "CM" to 900)
val romanNumbers = listOf(
    "MMM" to 3000,
    "MM" to 2000,
    "M" to 1000,
    "CM" to 900, //
    "D" to 500,
    "CD" to 400,
    "CCC" to 300,
    "CC" to 200,
    "C" to 100,
    "XC" to 90, //
    "L" to 50,
    "XL" to 40,
    "XXX" to 30,
    "XX" to 20,
    "X" to 10,
    "IX" to 9, //
    "V" to 5,
    "IV" to 4,
    "III" to 3,
    "II" to 2,
    "I" to 1
)

class RomanNumberFormatException(message: String) : Exception(message)

fun String.romanToInt(): Int {
    var str = this.uppercase()

    var minNum = Int.MAX_VALUE
    var number = 0

    romanNumbers.forEach { pair ->
        if (str.startsWith(pair.first)) {
            if (minNum > pair.second) {
                minNum = when {
                    pair.second % 10 == 0 -> 10.0.pow(pair.second.toString().length.toDouble()).toInt()
                    pair.second % 9 == 0 -> pair.second / 9
                    pair.second % 5 == 0 -> pair.second * 8 / 10
                    pair.second % 4 == 0 -> pair.second / 4
                    else -> 0
                }

                number += pair.second
                str = str.removePrefix(pair.first)
            } else {
                throw RomanNumberFormatException("incorrect entry of the Roman number")
            }
        }
    }

    if (str != "") {
        throw RomanNumberFormatException("incorrect entry of the Roman number")
    }
    return number
}

fun Int.toRomanNumber(): String {
    var num = this
    var result = ""

    if (num <= 0) {
        throw Exception("there are no zero or negative numbers in the Roman system")
    }

    if (num > MAX_ROMAN_NUMBER) {
        throw Exception("Roman numbers cannot be greater than 3999")
    }

    while (num != 0) {
        var max1 = 0
        romanCharset.values.forEach {
            if (it in (max1 + 1)..num) {
                max1 = it
            }
        }
        var max2 = 0
        numbersByRuleOfSubtraction.values.forEach {
            if (it in (max2 + 1)..num) {
                max2 = it
            }
        }

        if (max1 > max2) {
            num -= max1
            result += romanCharset.entries.find { it.value == max1 }?.key
        } else {
            num -= max2
            result += numbersByRuleOfSubtraction.entries.find { it.value == max2 }?.key
        }
    }

    return result
}
