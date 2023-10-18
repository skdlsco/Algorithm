package `10815`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val set = TreeSet<Int>()
    reader.readLine().split(" ").map { it.toInt() }.forEach { set.add(it) }
    val M = reader.readLine().toInt()
    val ans = reader.readLine().split(" ").map { it.toInt() }.map { set.contains(it) }.map {
        if (it)
            1
        else 0
    }
    writer.write(ans.joinToString(" "))
    writer.flush()
}