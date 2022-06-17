package clear

import java.util.*
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val C = scanner.nextInt()
    val arr = Array<Int>(N) { 0 }
    var minLength = 1
    var maxLength = 0
    var answer = minLength

    repeat(N) {
        arr[it] = scanner.nextInt()
    }
    arr.sort()
    maxLength = arr.last() - arr.first()
    while (minLength <= maxLength) {
        var cnt = 1
        val midLength = (minLength + maxLength) / 2
        var start = arr.first()
        arr.forEach {
            if (it - start >= midLength) {
                start = it
                cnt++
            }
        }
        if (cnt >= C) {
            answer = midLength
            minLength = midLength + 1
        } else
            maxLength = midLength - 1
    }
    println(answer)
}