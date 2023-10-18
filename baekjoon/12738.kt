package `12015`

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
    lis.add(arr[0])

    for (i in 1 until N) {
        val cur = arr[i]
        var left = 0
        var right = lis.size
        if (cur > lis.last()) {
            lis.add(cur)
            continue
        }
        while (left < right) {
            val mid = (left + right) / 2
            if (lis[mid] < cur) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        lis[right] = cur
    }
    writer.write("${lis.size}\n")
    writer.flush()
}