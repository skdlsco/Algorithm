package `29790`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, U, L) = reader.readLine().split(" ").map { it.toInt() }
    if (N >= 1000 && (U >= 8000 || L >= 260))
        println("Very Good")
    else if (N >= 1000)
        println("Good")
    else
        println("Bad")
}
    