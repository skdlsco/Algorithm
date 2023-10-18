package `28070`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun stringDateToInt(date: String): Int {
    val dateSplit = date.split("-").map { it.toInt() }
    return dateSplit[0] * 12 + dateSplit[1] - 1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val calendar = Array<Int>(120001) { 0 }
    repeat(N) {
        val (startDate, endDate) = reader.readLine().split(" ")

        calendar[stringDateToInt(startDate)]++
        calendar[stringDateToInt(endDate) + 1]--
    }
    var maxValue = 0
    var cnt = 0
    var result = 0
    for (i in calendar.indices) {
        cnt += calendar[i]
        if (maxValue < cnt) {
            result = i
            maxValue = cnt
        }
    }
    val year = result / 12
    val month = result % 12 + 1
    writer.write("${year}-${if (month < 10) "0" else ""}${month}")
    writer.flush()
}
