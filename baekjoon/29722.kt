package `29722`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (year, month, day) = reader.readLine().split("-").map { it.toInt() }
    val N = reader.readLine().toInt()
    val sum = year * 360 + (month - 1) * 30 + (day - 1) + N
    writer.write("${"%02d".format(sum / 360)}-${"%02d".format((sum % 360) / 30 + 1)}-${"%02d".format(sum % 30 + 1)}\n")
    writer.flush()
}
    