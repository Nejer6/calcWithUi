package extensions

val romanCharset = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
val numbersByRuleOfSubtraction = mapOf("IV" to 4, "IX" to 9, "XL" to 40, "XC" to 90, "CD" to 400, "CM" to 900)

class RomanNumberFormatException(message: String): Exception(message)

fun String.romanToInt(): Int {
    val str = this.uppercase()

    str.forEach {
        if (!romanCharset.contains(it)) {
            throw RomanNumberFormatException("The string has characters not related to the Roman notation")
        }
    }

    val nums = mutableListOf<Int>()

    var i = 0
    while (i < str.length) {
        if (i + 1 < str.length) {
            val numberByRuleOfSubtraction = numbersByRuleOfSubtraction[str.slice(i .. i + 1)]
            if (numberByRuleOfSubtraction != null) {
                nums.add(numberByRuleOfSubtraction)
                i += 2
                continue
            }
        }
        nums.add(romanCharset[str[i]]!!)
        i++
    }

    var minNum = nums.first()
    var number = nums.first()
    for (i in 1 until nums.size) {
        if (nums[i] > minNum) {
            throw RomanNumberFormatException("incorrect entry of the Roman number")
        }
        minNum = nums[i]
        number += nums[i]
    }

    return number
}

fun Int.toRomanNumber(): String {
    var num = this
    var result = ""

    if (num <= 0) {
        throw Exception("there are no zero or negative numbers in the Roman system")
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
            if (it in (max2 + 1) .. num) {
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
