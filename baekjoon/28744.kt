package `28744`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

val MOD = 1_000_000_007

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }
    // k..N의 합
    val A = Array<Long>(N) { 0 }
    A[N - 1] = arr[N - 1]
    for (i in N - 2 downTo 0) {
        A[i] = (arr[i] + A[i + 1]) % MOD
    }
    val B = Array<Long>(N) { 0 }
    for (i in N - 2 downTo 0) {
        B[i] = ((arr[i] * A[i + 1]) % MOD + B[i + 1]) % MOD
    }
    val C = Array<Long>(N) {0}
    for (i in N - 3 downTo 0) {
        C[i] = ((arr[i] * B[i + 1]) % MOD + C[i + 1]) % MOD
    }
    writer.write("${C[0]}\n")
    writer.flush()
}