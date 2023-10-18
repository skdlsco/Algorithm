package `5597`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val arr = Array<Boolean>(30) { true }
    for ( i in 0 until 28) {
        val target = reader.readLine().toInt() - 1
        arr[target] = false
    }

    for (i in 0 until 30) {
        if (arr[i])
            println(i + 1)
    }
}