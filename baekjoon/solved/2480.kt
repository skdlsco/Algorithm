package `2480`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n1, n2, n3) = reader.readLine().split(" ").map { it.toInt() }

    if (n1 == n2 && n2 == n3) {
        println(10000 + n1 * 1000)
    } else if (n1 == n2 || n2 == n3 || n1 == n3) {
        val same = ((n1 + n2 + n3) - (n1 xor n2 xor n3)) / 2
        println(1000 + same * 100)
    } else {
        println(max(n1, max(n2, n3)) * 100)
    }
}