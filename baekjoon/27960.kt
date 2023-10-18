package `27960`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B) = reader.readLine().split(" ").map { it.toInt() }

    val C = A xor B
    println(C)
}