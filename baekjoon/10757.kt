package `10757`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var N1 = scanner.next().reversed()
    var N2 = scanner.next().reversed()
    var result = String()
    var carry = 0

    if (N1.length < N2.length) {
        val tmp = N1
        N1 = N2
        N2 = tmp
    }

    N1.forEachIndexed { index, c ->
        var num = c.digitToInt() + carry

        if (N2.length > index)
            num += N2[index].digitToInt()
        carry = if (num > 9) 1 else 0
        num %= 10

        result += (num + '0'.code).toChar()
    }
    if (carry == 1)
        result += '1'.code.toChar()
    println(result.reversed())
}