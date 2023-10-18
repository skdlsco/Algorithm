package `10819`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun getResult(arr: Array<Int>, nums: Array<Int>, size: Int, idx: Int): Int {
    var result = 0
    if (idx == size) {
        var sum = 0
        (1 until size).forEach {
            sum += abs(arr[nums[it - 1]] - arr[nums[it]])
        }
        return (sum)
    }
    (0 until size).forEach {
        if (it !in nums.slice(0 until idx))
        {
            nums[idx] = it
            val value = getResult(arr, nums, size, idx + 1)
            if (value > result)
                result = value
        }
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val nums = Array<Int>(N) { 0 }
    println(getResult(arr, nums, N, 0))
}