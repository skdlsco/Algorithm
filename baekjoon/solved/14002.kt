package `14002`

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
    lis.add(0)
    val path = Array<Int>(N) { it }
    for (i in 1 until N) {
        var left = 0
        var right = lis.size
        while (left < right) {
            val mid = (left + right) / 2
            if (arr[lis[mid]] < arr[i]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        if (right >= lis.size) {
            lis.add(i)
        } else {
            lis[right] = i
        }
        if (right != 0)
            path[i] = lis[right - 1]
    }
    writer.write("${lis.size}\n")
    val result = ArrayList<Int>()
    var idx = lis.last()
    while (path[idx] != idx) {
        result.add(idx)
        idx = path[idx]
    }
    result.add(idx)
    writer.write(
        "${result.reversed().joinToString(" ") { arr[it].toString() }}\n"
    )
    writer.flush()
}