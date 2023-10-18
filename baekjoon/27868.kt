package `27868`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val a = reader.readLine()
    val b = reader.readLine()
    val c = reader.readLine()
    println(c.reversed())
}