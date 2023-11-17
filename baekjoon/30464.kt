package `I`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val temp = Array<Int>(N) { -1 }
    var ans = -1
    temp[0] = 0
    for (i in 0 until N - 1) {
        val next = i + arr[i]
        if (temp[i] == -1 || next >= N)
            continue
        temp[next] = maxOf(temp[next], temp[i] + 1)
    }
    ans = maxOf(ans, temp[N - 1])
    temp[N - 1] = -1
    for (i in N - 1 downTo 0) {
        val next = i - arr[i]
        if (temp[i] == -1 || next < 0)
            continue
        temp[next] = maxOf(temp[next], temp[i] + 1)
    }
    for (i in 0 until N - 1) {
        val next = i + arr[i]
        if (temp[i] == -1 || next >= N)
            continue
        temp[next] = maxOf(temp[next], temp[i] + 1)
    }
    ans = maxOf(ans, temp[N - 1])
    writer.write("${ans}")
    writer.flush()
}
    