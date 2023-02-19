package `2525`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (hour, minute) = reader.readLine().split(" ").map { it.toInt() }
    val append = reader.readLine().toInt()
    val extra = if ((minute + (append % 60)) >= 60) 1 else 0
    println("${(hour + (append / 60) + extra) % 24} ${(minute + (append % 60)) % 60}")
}