package `28086`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import kotlin.math.floor

fun eightToTen(eight: String): Long {
    var result = 0L
    var cur = 0
    val isMinus = eight[cur] == '-'
    if (eight[cur] == '-')
        cur++
    while (cur < eight.length) {
        result *= 8
        result += eight[cur].digitToInt()
        cur++
    }
    return result * if (isMinus) -1 else 1
}

fun tenToEight(ten: Long): String {
    val isMinus = ten < 0
    var temp = ten
    if (isMinus)
        temp *= -1
    val result = StringBuilder()
    if (temp == 0L)
        result.append("0")
    while (temp > 0) {
        result.append("${temp % 8}")
        temp /= 8
    }
    if (isMinus)
        result.append('-')
    return result.reverse().toString()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val line = reader.readLine()
    var cur = 0
    if (line[0] == '-')
        cur++
    while (line[cur] in "01234567")
        cur++
    val A = line.slice(0 until cur)
    val oper = line[cur]
    val B = line.slice(cur + 1 until line.length)
    val tenA = eightToTen(A)
    val tenB = eightToTen(B)
    when (oper) {
        '/' -> if (tenB == 0L)
            writer.write("invalid")
        else
            writer.write(tenToEight(floor(tenA / tenB.toDouble()).toLong()))

        '+' ->
            writer.write(tenToEight(tenA + tenB))

        '-' ->
            writer.write(tenToEight(tenA - tenB))

        '*' ->
            writer.write(tenToEight(tenA * tenB))
    }
    writer.flush()
}