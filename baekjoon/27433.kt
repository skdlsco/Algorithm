import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun recursive(N: Int): Long {
    if (N == 0)
        return 1L
    else return recursive(N - 1) * N
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    writer.write("${recursive(N)}\n")
    writer.flush()
}