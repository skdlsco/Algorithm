package `27964`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val set = HashSet<String>()
    reader.readLine().split(" ").forEach { set.add(it) }
    val cheese = set.count { it.endsWith("Cheese") }
    println(if (cheese >= 4) "yummy" else "sad")
}