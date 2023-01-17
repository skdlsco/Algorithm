package baekjoon.solved

import java.util.*

data class PaperCount(var t1: Int, var t2: Int, var t3: Int) {
    fun clearData() {
        t1 = 0
        t2 = 0
        t3 = 0
    }

    fun addData(data: Int) {
        when (data) {
            -1 -> t1++
            0 -> t2++
            1 -> t3++
        }
    }
}

fun count(arr: Array<Array<Int>>, N: Int, startX: Int, startY: Int): PaperCount {
    if (N == 1)
        return PaperCount(0, 0, 0).apply { addData(arr[startY][startX]) }
    val nextN = N / 3
    val slicedArr = (0..2).flatMap {
        val nextX = startX + it * nextN
        (0..2).map {
            val nextY = startY + it * nextN
            count(arr, nextN, nextX, nextY)
        }
    }
    val result = PaperCount(0, 0, 0)
    slicedArr.forEach {
        result.t1 += it.t1
        result.t2 += it.t2
        result.t3 += it.t3
    }
    when {
        result.t1 + result.t2 == 0 -> {
            result.clearData()
            result.t3 = 1
        }
        result.t2 + result.t3 == 0 -> {
            result.clearData()
            result.t1 = 1
        }
        result.t1 + result.t3 == 0 -> {
            result.clearData()
            result.t2 = 1
        }
    }
    return result
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = Array<Array<Int>>(N) {
        Array(N) {
            scanner.nextInt()
        }
    }
    val count = count(arr, N, 0, 0)
    println(count.t1)
    println(count.t2)
    println(count.t3)
}