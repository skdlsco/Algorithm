package `17219`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val map = HashMap<String, String>()
    repeat(n) {
        val (key, value) = reader.readLine().split(" ")
        map[key] = value
    }

    repeat(m) {
        val key = reader.readLine()
        writer.write(map[key]!!)
        writer.newLine()
    }
    writer.flush()
}