package `2108`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.roundToInt

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N) { reader.readLine().toInt() }
    arr.sort()
    println((arr.sum() / N.toDouble()).roundToInt())
    println(arr[N / 2])
    var max = 1
    var cnt = 1
    var first = true
    var num = arr[0]
    for (i in 1 until N) {
        if (arr[i] == arr[i - 1]) {
            cnt++
            if (cnt > max) {
                first = true
                max = cnt
                num = arr[i]
            } else if (cnt == max) {
                if (first) {
                    num = arr[i]
                    first = false
                }
            }
        } else {
            cnt = 1
        }
    }
    if (max != 1)
        println(num)
    else if (N == 1)
        println(arr[0])
    else println(arr[1])
    println(arr.last() - arr.first())
}