package `1920`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun isExist(list: IntArray, target: Int): Int {
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (list[mid] == target)
            return 1
        else if (list[mid] < target)
            left = mid + 1
        else
            right = mid - 1
    }

    return 0
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()
    val M = reader.readLine().toInt()
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        writer.write("${isExist(arr, it)}\n")
    }
    writer.flush()
}