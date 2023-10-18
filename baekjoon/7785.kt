package `7785`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val set = TreeSet<String>()
    repeat(N) {
        val (name, behavior) = reader.readLine().split(" ")
        if (behavior == "enter")
            set.add(name)
        else
            set.remove(name)
    }
    writer.write(set.sortedDescending().joinToString("\n"))
    writer.flush()
}