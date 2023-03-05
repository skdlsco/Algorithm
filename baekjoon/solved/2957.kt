package `2957`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val tree = TreeMap<Int, Int>()
    val N = reader.readLine().toInt()
    var c = 0L
    repeat(N) {
        val num = reader.readLine().toInt()
        if (tree.isEmpty()) {
            writer.write("${c}\n")
            tree[num] = 1
            return@repeat
        }
        val lowerKey = tree.ceilingKey(num)
        val upperKey = tree.floorKey(num)
        val lower = if (lowerKey == null) 0 else tree[lowerKey]!!
        val upper = if (upperKey == null) 0 else tree[upperKey]!!
        val depth = max(lower, upper)
        c += depth
        tree[num] = depth + 1
        writer.write("${c}\n")
    }
    writer.flush()
}