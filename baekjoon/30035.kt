package `30035`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap
import kotlin.math.min

data class Data(var start: Int, var end: Int) {
    val capacity get() = end - start + 1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, T) = reader.readLine().split(" ").map { it.toInt() }
    val tier = TreeMap<String, Data>()
    var cur = 1
    repeat(T) {
        val (name, query) = reader.readLine().split(" ")
        val num = query.trim('%').toInt()
        val isPercentage = query.contains('%')
        val rest = N - cur + 1
        var size = if (!isPercentage) {
            min(rest, num)
        } else {
            (rest.toLong() * num / 100).toInt()
        }
        var suffix = 1
        val start = cur
        val segSize = (size / 4) + if (size % 4 == 0) 0 else 1
        while (size > 0) {
            val realSegSize = min(size, segSize)
            val end = cur + realSegSize - 1
            tier[name + suffix] = Data(cur, end)
            size -= realSegSize
            cur += realSegSize
            suffix++
        }
        val end = cur - 1
        if (suffix > 1)
            tier[name] = Data(start, end)
    }
    val friendTier = reader.readLine()
    if (cur <= N) {
        writer.write("Invalid System")
    } else if (tier.contains(friendTier)) {
        val (s, e) = tier[friendTier]!!
        writer.write("${s} ${e}")
    } else {
        writer.write("Liar")
    }
    writer.flush()
}
    