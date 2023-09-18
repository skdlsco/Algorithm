package `5635`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val arr = ArrayList<Pair<String, String>>()
    repeat(n) {
        val (name, d, m, y) = reader.readLine().split(" ")
        arr.add(Pair(name, y + m.padStart(2, '0') + d.padStart(2, '0')))
    }
    val old = arr.minByOrNull { it.second }
    val young = arr.maxByOrNull { it.second }
    writer.write("${young?.first}\n")
    writer.write("${old?.first}\n")
    writer.flush()
}
    