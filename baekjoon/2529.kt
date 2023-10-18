package `2529`

import java.io.BufferedReader
import java.io.InputStreamReader

fun getMax(ine: Array<Char>) {
    val nums = ArrayList<Int>()
    val size = ine.size
    nums.addAll((0..9))
    ine.forEachIndexed { index, c ->
        if (c == '>') {
            print(nums.last())
            nums.remove(nums.last())
        } else {
            var cnt = ine.slice(index until size).indexOf('>')
            if (cnt == -1)
                cnt = ine.slice(index until size).size
            cnt++
            print(nums[nums.size - cnt])
            nums.remove(nums[nums.size - cnt])
        }
    }
    print(nums.last())
    println()
}

fun getMin(ine: Array<Char>) {
    val nums = ArrayList<Int>()
    val size = ine.size
    nums.addAll((0..9))
    nums.reverse()
    ine.forEachIndexed { index, c ->
        if (c == '<') {
            print(nums.last())
            nums.remove(nums.last())
        } else {
            var cnt = ine.slice(index until size).indexOf('<')
            if (cnt == -1)
                cnt = ine.slice(index until size).size
            cnt++
            print(nums[nums.size - cnt])
            nums.remove(nums[nums.size - cnt])
        }
    }
    print(nums.last())
    println()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val K = reader.readLine().toInt()
    val ine = reader.readLine().trim().split(" ").map { it[0] }.toTypedArray()
    getMax(ine)
    getMin(ine)
}