import extensions.RomanNumberFormatException
import extensions.romanToInt
import extensions.toRomanNumber

class FormatException :
    Exception("the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)")

class DifferentNumberSystemException : Exception("different number systems are used simultaneously")
class OperandIsNotNumberException : Exception("one ore more operands is not a number")

fun calculate(str: String): String {
    val splitStr = str.split(' ').filter { it != "" }
    val operations = listOf("+", "-", "/", "*")

    if (splitStr.size != 3 || !operations.contains(splitStr[1])) {
        throw FormatException()
    }

    val operator = splitStr[1]
    val numStr1 = splitStr[0]
    val numStr2 = splitStr[2]

    try {
        val num1 = numStr1.romanToInt()
        try {
            val num2 = numStr2.romanToInt()
            return execute(num1, num2, operator).toRomanNumber()
        } catch (e: RomanNumberFormatException) { //num2 - не roman
            try {
                numStr2.toInt()
                throw DifferentNumberSystemException()
            } catch (e: NumberFormatException) { //num2 - не int
                throw OperandIsNotNumberException()
            }
        }
    } catch (e: RomanNumberFormatException) { //num1 - не roman
        try {
            val num1 = numStr1.toInt()
            try {
                val num2 = numStr2.toInt()
                return execute(num1, num2, operator).toString()
            } catch (e: NumberFormatException) { //num2 - не int
                try {
                    numStr2.romanToInt()
                    throw DifferentNumberSystemException()
                } catch (e: RomanNumberFormatException) { //num2 - не roman
                    throw OperandIsNotNumberException()
                }
            }
        } catch (e: NumberFormatException) { //num1 - не int
            throw OperandIsNotNumberException()
        }
    }
//    try {
//        val num1 = splitStr[0].toInt()
//        try {
//            val num2 = splitStr[2].toInt()
//            return execute(num1, num2, operator).toString()
//        } catch (e: NumberFormatException) {
//            splitStr[2].romanToInt()
//            throw DifferentNumberSystemException()
//        }
//    } catch (e: NumberFormatException) {
//
//        val num1 = splitStr[0].romanToInt()
//        try {
//            val num2 = splitStr[2].romanToInt()
//            val result = execute(num1, num2, operator)
//            return result.toRomanNumber()
//        } catch (e: RomanNumberFormatException) {
//            try {
//                splitStr[2].toInt()
//                throw DifferentNumberSystemException()
//            } catch (e: NumberFormatException) {
//                throw OperandIsNotNumberException()
//            }
//        }
//
//    }
}

private fun execute(num1: Int, num2: Int, operation: String): Int {
    return when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> throw Exception("the operator is not defined")
    }
}