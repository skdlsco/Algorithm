package `30034`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val sp = reader.readLine().split(" ")
    val M = reader.readLine().toInt()
    val np = reader.readLine().split(" ")
    val K = reader.readLine().toInt()
    val combineSet = reader.readLine().split(" ").toSet()
    val delimiterSet = HashSet<String>()
    delimiterSet.add(" ")
    for (c in sp) {
        if (!combineSet.contains(c))
            delimiterSet.add(c)
    }
    for (c in np) {
        if (!combineSet.contains(c))
            delimiterSet.add(c)
    }
    val S = reader.readLine().toInt()
    val str = reader.readLine()
    val line = StringBuilder()
    for (c in str) {
        if (delimiterSet.contains(c.toString())) {
            if (line.isNotBlank()) {
                writer.write(line.toString())
                writer.newLine()
                line.clear()
            }
        } else {
            line.append(c)
        }
    }
    if (line.isNotBlank())
        writer.write(line.toString())
    writer.flush()
}
    