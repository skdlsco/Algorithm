import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun recursive(N: Int): Long {
    return when (N) {
        0 -> 0L
        1 -> 1L
        2 -> 1L
        else -> recursive(N - 1) + recursive(N - 2)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    writer.write("${recursive(N)}\n")
    writer.flush()
}