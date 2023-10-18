package `12629`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun solve(): Int {
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N) { 0 }
    var swapCount = 0

    for (i in 0 until N) {
        val line = reader.readLine()
        val lastIdx = line.lastIndexOf("1")
        arr[i] = lastIdx
    }
    // i 0 ~ N - 1까지
    // arr[i] = 해당 i번재 행의 제일 오른쪽 1의 위치
    // arr[i]의 값이 i보다 큰 경우 위치가 교체되어야 한다.
    for (i in 0 until N - 1) {
        var target = i
        if (arr[i] <= i)
            continue
        for (j in i + 1 until N) {
            if (arr[j] <= i) {
                target = j
                break
            }
        }
        for (j in target downTo i + 1) {
            val temp = arr[j]
            arr[j] = arr[j - 1]
            arr[j - 1] = temp
            swapCount++
        }
    }
    return swapCount
}

fun main() {
    val T = reader.readLine().toInt()
    repeat(T) {
        val result = solve()
        writer.write("Case #${it + 1}: ${result}\n")
    }
    writer.flush()
}