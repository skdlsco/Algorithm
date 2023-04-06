package `14003`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val lis = ArrayList<Int>()
    val lisIdxArr = Array<Int>(N) { it }
    lis.add(arr[0])
    lisIdxArr[0] = 0
    for (i in 1 until N) {
        if (lis.last() < arr[i]) {
            lis.add(arr[i])
            lisIdxArr[i] = lis.size - 1
            continue
        }
        var left = 0
        var right = lis.size - 1
        while (left < right) {
            val mid = (left + right) / 2
            if (lis[mid] < arr[i]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        lis[left] = arr[i]
        lisIdxArr[i] = left
    }
    writer.write("${lis.size}\n")
    val result = ArrayList<Int>()
    var target = lis.size - 1
    for (i in N - 1 downTo 0) {
        if (target == lisIdxArr[i]) {
            result.add(arr[i])
            target--
        }
    }
    writer.write(
        "${result.reversed().joinToString(" ")}\n"
    )
    writer.flush()
}