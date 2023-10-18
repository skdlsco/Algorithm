package `10448`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun find(arr: Array<Int>, target: Int): Boolean {
    for (i in arr) {
        for (j in arr) {
            for (k in arr) {
                if (i + j + k == target) {
                    return true
                }
            }
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(45) { 0 }
    arr[0] = 1
    for (i in 1 until 45) {
        arr[i] = arr[i - 1] + i + 1
    }
    repeat(N) {
        val target = reader.readLine().toInt()

        println(if (find(arr, target)) 1 else 0)
    }
}