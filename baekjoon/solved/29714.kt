package `29714`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = TreeMap<Int, Int>()
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        map[it] = (map[it] ?: 0) + 1
    }
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val a = reader.readLine().split(" ").map { it.toInt() }
        val condition = TreeMap<Int, Int>()
        for (i in 1 until a.size) {
            condition[a[i]] = (condition[a[i]] ?: 0) + 1
        }
        val valid = condition.all { (map[it.key] ?: 0) >= it.value }
        val b = reader.readLine().split(" ").map { it.toInt() }
        if (valid) {
            for ((k, v) in condition) {
                map[k] = map[k]!! - v
            }
            for (i in 1 until b.size) {
                map[b[i]] = (map[b[i]] ?: 0) + 1
            }
        }
    }
    val sum = map.map { it.value }.sumOf { it }
    writer.write("${sum}\n")
    for ((k, v) in map) {
        for (i in 0 until v)
            writer.write("${k} ")
    }
    if (sum > 0)
        writer.newLine()
    writer.flush()
}
    