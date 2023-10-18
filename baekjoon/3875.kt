package `3875`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val (w, d) = reader.readLine().split(" ").map { it.toInt() }
        if (w == 0 && d == 0)
            break
        val map = HashMap<Int, Int>()
        var sum = 0
        val side = reader.readLine().split(" ").map { it.toInt() }
        reader.readLine().split(" ").map { it.toInt() }.forEach {
            if (map.contains(it))
                map[it] = map[it]!! + 1
            else
                map[it] = 1
            sum += it
        }
        side.forEach {
            if (map.contains(it) && map[it]!! > 0) {
                map[it] = map[it]!! - 1
            } else
                sum += it
        }
        writer.write("${sum}\n")
    }
    writer.flush()
}