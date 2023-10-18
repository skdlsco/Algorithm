package `29719`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MOD = 1000000007L
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    var result = 1L
    var except = 1L
    repeat(N) {
        result = (result * M) % MOD
        except = (except * (M - 1)) % MOD
    }
    writer.write("${(MOD + result - except) % MOD}\n")
    writer.flush()
}
    