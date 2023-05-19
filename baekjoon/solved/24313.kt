package `24313`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (a1, a0) = reader.readLine().split(" ").map { it.toInt() }
    val c = reader.readLine().toInt()
    val n0 = reader.readLine().toInt()
    if (n0 * a1 + a0 > c * n0)
        println(0)
    else if (a1 > c)
        println(0)
    else
        println(1)
}