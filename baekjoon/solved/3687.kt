package `3687`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun makeMax(match: Int): String {
    var cur = match
    val number = StringBuilder()
    if (match % 2 == 1) {
        cur -= 3
        number.append('7')
    }
    repeat(cur / 2) {
        number.append('1')
    }
    return number.toString()
}

fun appendPrefix(number: StringBuilder, match: Int): Int {
    if (match > 14 && match % 7 == 3) {
        number.append("200")
        return 17
    }
    if (match > 7 && match % 7 < 5) {
        when (match % 7) {
            0 -> number.append("8")
            1 -> number.append("10")
            2 -> number.append("18")
            3 -> number.append("22")
            4 -> number.append("20")
        }
        return 7 + (match % 7)
    }
    when (match % 7) {
        2 -> number.append('1')
        3 -> number.append('7')
        4 -> number.append('4')
        5 -> number.append('2')
        6 -> number.append('6')
    }
    return match % 7
}

fun makeMin(match: Int): String {
    var cur = match
    val number = StringBuilder()
    cur -= appendPrefix(number, match)
    val eightCount = cur / 7
    repeat(eightCount) {
        number.append('8')
    }
    return number.toString()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()


    repeat(T) {
        val match = reader.readLine().toInt()
        writer.write("${makeMin(match)} ${makeMax(match)}\n")
    }
    writer.flush()
    writer.close()
}