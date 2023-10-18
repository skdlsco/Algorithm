package `25304`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var X = reader.readLine().toInt()
    val N = reader.readLine().toInt()
    repeat(N) {
        val (price, cnt) = reader.readLine().split(" ").map { it.toInt() }
        X -= price * cnt
    }

    println(if (X == 0) "Yes" else "No")
}