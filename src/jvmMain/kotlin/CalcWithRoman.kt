import extensions.romanToInt
import extensions.toRomanNumber

fun calculate(str: String): String {
    val splitStr = str.split(' ')
    val operations = listOf("+", "-", "/", "*")
    val formatException = Exception("the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)")
    val differentNumberSystemException = Exception("different number systems are used simultaneously")
    val operandIsNotNumber = Exception("one ore more operands is not a number")

    if (splitStr.size != 3 || !operations.contains(splitStr[1])) {
        throw formatException
    }

    val operator = splitStr[1]

    try {
        val num1 = splitStr[0].toInt()
        try {
            val num2 = splitStr[2].toInt()
            return execute(num1, num2, operator).toString()
        } catch (e: Exception) {
            try {
                splitStr[2].romanToInt()
                throw differentNumberSystemException
            } catch (e: Exception) {
                throw operandIsNotNumber
            }
        }
    } catch (e : Exception) {
        try {
            val num1 = splitStr[0].romanToInt()
            try {
                val num2 = splitStr[2].romanToInt()
                return execute(num1, num2, operator).toRomanNumber()
            } catch (e: Exception) {
                try {
                    splitStr[2].toInt()
                    throw differentNumberSystemException
                } catch (e: Exception) {
                    throw operandIsNotNumber
                }
            }
        } catch (e: Exception) {
            throw operandIsNotNumber
        }
    }
}

fun execute(num1: Int, num2: Int, operation: String): Int {
    return when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> num1 / num2
        else -> throw Exception("the operator is not defined")
    }
}