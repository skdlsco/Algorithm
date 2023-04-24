package `20055`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val robotArr = Array<Boolean>(N) { false }
    var cnt = 0
    while (arr.count { it == 0 } < K) {
        // rotate
        val temp = arr.last()
        for (i in N * 2 - 1 downTo 1) {
            arr[i] = arr[i - 1]
        }
        arr[0] = temp
        for (i in N - 2 downTo 0) {
            robotArr[i + 1] = robotArr[i]
        }
        robotArr[N - 1] = false
        robotArr[0] = false
        // move robot
        for (i in N - 1 downTo 1) {
            if (!robotArr[i] && robotArr[i - 1] && arr[i] > 0) {
                robotArr[i] = true
                robotArr[i - 1] = false
                arr[i]--
            }
        }
        robotArr[N - 1] = false
        // put robot
        if (arr[0] > 0) {
            arr[0]--
            robotArr[0] = true
        }
        cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}