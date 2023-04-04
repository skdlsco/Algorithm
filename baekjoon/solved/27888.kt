package `27888`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val stationBook = HashMap<String, Int>(1000000)
    val characteristicsBook = HashMap<String, Int>()
    repeat(N) {
        stationBook[reader.readLine()] = 0
    }
    val R = reader.readLine().toInt()
    val table = Array<Int>(1024) { 0 }
    repeat(R) {
        val input = reader.readLine().split(" ")
        if (input[0] == "U") {
            val stationName = input[1]
            val prevKey = stationBook[stationName]!!
            for (i in 0 until 1024) {
                if (i - (i and prevKey) == 0) {
                    table[i]--
                }
            }
            val characteristicsIds = input[2].split(",").map {
                if (!characteristicsBook.containsKey(it))
                    characteristicsBook[it] = characteristicsBook.size
                characteristicsBook[it]!!
            }
            val key = characteristicsIds.sumOf { 1 shl it }
            stationBook[stationName] = key
            for (i in 0 until 1024) {
                if (i - (i and key) == 0) {
                    table[i]++
                }
            }
        } else {
            val characteristicsIds = input[1].split(",").map { characteristicsBook[it] }
            if (characteristicsIds.any { it == null }) {
                writer.write("0\n")
                return@repeat
            }
            val key = characteristicsIds.sumOf { 1 shl it!! }
            writer.write("${table[key]}\n")
        }
    }
    writer.flush()
}