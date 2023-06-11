package `19845`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun getHeight(arr: IntArray, x: Int, y: Int): Int {
    var s = y
    var e = arr.size
    while (s < e) {
        val m = (s + e) / 2
        if (arr[m] >= x)
            s = m + 1
        else
            e = m
    }
    return max(0, e - y)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    //  모든 1 ≤ y ≤ N − 1에 대해 ay ≥ ay+1이다.
    // -> 내림차순 데이터가 정렬되어 들어온다.
    // -> 이분탐색으로 레이저의 영향을 안받기 시작하는 층을 알 수 있다.
    val arr = IntArray(N + 1)
    arr[0] = 0
    val line = reader.readLine().split(" ").map { it.toInt() }
    for (i in 0 until N) {
        arr[i + 1] = line[i]
    }

    repeat(Q) {
        val (x, y) = reader.readLine().split(" ").map { it.toInt() }
        val width = max(0, arr[y] - x + 1)
        val height = getHeight(arr, x, y)
        if (width == 0)
            writer.write("${0}\n")
        else
            writer.write("${width + height - 1}\n")
    }
    writer.flush()
}