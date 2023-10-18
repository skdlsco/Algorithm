package `13335`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, w, L) = reader.readLine().split(" ").map { it.toInt() }
    var time = 0
    val bridge = LinkedList<Int>()
    var weight = 0
    repeat(w) {
        bridge.add(0)
    }
    val trucks = reader.readLine().split(" ").map { it.toInt() }
    var idx = 0
    while (true) {
        if (weight == 0 && idx == n)
            break
        time++
        val exit = bridge.pop()
        weight -= exit
        if (idx == n)
            continue
        if (trucks[idx] + weight <= L) {
            bridge.add(trucks[idx])
            weight += trucks[idx]
            idx++
        } else
            bridge.add(0)
    }
    writer.write("${time}")
    writer.flush()
}