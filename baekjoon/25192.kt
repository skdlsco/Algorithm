import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val N = reader.readLine().toInt()
    val set = TreeSet<String>()
    var ans = 0
    repeat(N) {
        val line = reader.readLine()
        if (line == "ENTER")
            set.clear()
        else if (!set.contains(line)) {
            ans++
            set.add(line)
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
