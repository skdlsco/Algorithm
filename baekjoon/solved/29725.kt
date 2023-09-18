package `29725`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val map = HashMap<Char, Int>().apply {
        put('K', 0)
        put('k', -0)
        put('P', 1)
        put('p', -1)
        put('N', 3)
        put('n', -3)
        put('B', 3)
        put('b', -3)
        put('R', 5)
        put('r', -5)
        put('Q', 9)
        put('q', -9)
        put('.', 0)
    }
    var sum = 0
    for (y in 0 until 8) {
        val row = reader.readLine()
        for (x in 0 until 8) {
            sum += map[row[x]]!!
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
    