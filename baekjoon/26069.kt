import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val N = reader.readLine().toInt()
    val set = TreeSet<String>()
    set.add("ChongChong")
    repeat(N) {
        val (A, B) = reader.readLine().split(" ")
        if (set.contains(A) || set.contains(B)) {
            set.add(A)
            set.add(B)
        }
    }
    writer.write("${set.size}\n")
    writer.flush()
}
