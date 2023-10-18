import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun gcd(a: Long, b: Long): Long {
    if (b == 0L)
        return a
    return gcd(b, a % b)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (A, B) = reader.readLine().split(" ").map { it.toLong() }
        val ans = A * B / gcd(A, B)
        writer.write("${ans}\n")
    }
    writer.flush()
}