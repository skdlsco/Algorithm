package `28445`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val arr = reader.readLine().split(" ").plus(reader.readLine().split(" "))

    val set = TreeSet<String>()
    for (a in arr) {
        for (b in arr) {
            set.add("$a $b")
        }
    }
    val result = set.sorted().joinToString("\n")
    writer.write(result)
    writer.flush()
}