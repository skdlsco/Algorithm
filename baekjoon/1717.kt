package `1717`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun find(group: Array<Int>, v: Int): Int {
    if (group[v] == v)
        return v
    val vGroup = find(group, group[v])
    group[v] = vGroup
    return vGroup
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val group = Array<Int>(n + 1) { it }
    repeat(m) {
        val (op, a, b) = reader.readLine().split(" ").map { it.toInt() }
        val aGroup = find(group, a)
        val bGroup = find(group, b)
        if (op == 1) {
            if (aGroup == bGroup)
                writer.write("YES")
            else
                writer.write("NO")
            writer.newLine()
        } else {
            group[aGroup] = bGroup
        }
    }
    writer.flush()
}