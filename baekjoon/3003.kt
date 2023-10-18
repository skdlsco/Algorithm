package `3003`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val arr = reader.readLine().split(" ").map { it.toInt() }

    println("${1 - arr[0]} ${1 - arr[1]} ${2 - arr[2]} ${2 - arr[3]} ${2 - arr[4]} ${8 - arr[5]}")
}

