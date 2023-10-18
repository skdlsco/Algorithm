package `14889`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun getScore(arr: Array<Array<Int>>, team: Array<Int>, size: Int): Int {
    var sum = 0
    (0 until size).forEach { y ->
        (y until size).forEach { x ->
            sum += arr[team[y]][team[x]]
        }
    }
    return sum
}

fun getResult(arr: Array<Array<Int>>, nums: Array<Int>, size: Int, num: Int, idx: Int): Int {
    var result = Int.MAX_VALUE
    if (idx == size / 2) {
        val linkTeam = (0 until size).filter { it !in nums }.toTypedArray()
        val linkScore = getScore(arr, linkTeam, size / 2)
        val startScore = getScore(arr, nums, size / 2)
        return abs(linkScore - startScore)
    }
    (num until size).forEach {
        nums[idx] = it
        val value = getResult(arr, nums, size, it + 1, idx + 1)
        if (value < result)
            result = value
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { Array<Int>(N) { 0 } }
    repeat(N) {
        arr[it] = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    }
    repeat(N) { y ->
        repeat(y) { x ->
            arr[x][y] += arr[y][x]
        }
    }
    val nums = Array<Int>(N / 2) { 0 }
    println(getResult(arr, nums, N, 0, 0))
}