package `11478`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val str = reader.readLine()
    val set = TreeSet<String>()

    for (s in str.indices) {
        for (e in s  until str.length) {
            set.add(str.slice(s..e))
        }
    }
    writer.write("${set.size}\n")
    writer.flush()
}