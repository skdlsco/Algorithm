package `2890`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (R, C) = reader.readLine().split(" ").map { it.toInt() }
    val input = Array<String>(R) { reader.readLine() }
    input.sort()

    val arr = Array<Int>(9) { 0 }
    var rank = 0
    var minLen = Int.MAX_VALUE
    input.forEach {
        var num = 0
        var len = 0
        for (i in 1 until C - 1) {
            if (it[i] != '.') {
                num = it[i].digitToInt()
                break
            }
            len++
        }
        if (num != 0) {
            if (len < minLen) {
                minLen = len
                rank++
            }
            arr[num - 1] = rank
        }
    }
    arr.forEach {
        println(it)
    }
}