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
    val (A, B) = reader.readLine().split(" ").map { it.toLong() }
    val (C, D) = reader.readLine().split(" ").map { it.toLong() }
    val E = A * D + C * B
    val F = B * D
    writer.write("${E / gcd(E, F)} ${F / gcd(E, F)}\n")
    writer.flush()
}