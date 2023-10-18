package `6603`

import java.io.BufferedReader
import java.io.InputStreamReader

fun lotto(data: List<Int>, nums: Array<Int>, size: Int, num: Int, idx: Int) {
    if (idx == 6) {
        nums.forEach {
            print("${data[it]} ")
        }
        println()
        return
    }
    (num until size).forEach {
        nums[idx] = it
        lotto(data, nums, size, it + 1, idx + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var data = reader.readLine().split(" ").map { it.toInt() }
    while (data[0] != 0) {
        val N = data[0]
        val arr = data.subList(1, data.size)
        lotto(arr, Array<Int>(6) { 0 }, arr.size, 0, 0)
        println()
        data = reader.readLine().split(" ").map { it.toInt() }
    }
}