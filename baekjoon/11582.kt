package `11582`

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

var N: Int = 0
lateinit var temp: Array<Int>

fun merge(arr: Array<Int>, left: Int, mid: Int, right: Int) {
    var tempIdx = 0
    var leftIdx = left
    var rightIdx = mid

    while (leftIdx < mid && rightIdx < right) {
        if (arr[leftIdx] < arr[rightIdx])
            temp[tempIdx] = arr[leftIdx++]
        else
            temp[tempIdx] = arr[rightIdx++]
        tempIdx++
    }
    while (leftIdx < mid)
        temp[tempIdx++] = arr[leftIdx++]
    while (rightIdx < right)
        temp[tempIdx++] = arr[rightIdx++]
    (left until right).forEach {
        arr[it] = temp[it - left]
    }
}

fun partition(arr: Array<Int>, left: Int, right: Int, K: Int, k: Int) {
    val mid = (left + right) / 2
    if (left < right - 1) {
        partition(arr, left, mid, K, k * 2)
        partition(arr, mid, right, K, k * 2)
        if (k >= K)
            merge(arr, left, mid, right)
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    N = scanner.nextInt()
    val arr = Array<Int>(N) { -1 }
    temp = Array(N) { -1 }
    repeat(N) {
        arr[it] = scanner.nextInt()
    }
    val K = scanner.nextInt()
    partition(arr, 0, N, K, 1)
    var check = 0
    arr.forEachIndexed { index, i ->
        if (i == -1 && check == 0)
            check = index
        writer.write("$i ")
    }
    writer.flush()
    writer.close()
}

