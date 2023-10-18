package `2878`

import java.io.BufferedReader
import java.io.InputStreamReader

fun getFlattingDiffSum(arr: LongArray, start: Int, end: Int): Long {
    val flatPoint = arr[start]
    var sum = 0L
    for (i in start..end) {
        sum += arr[i] - flatPoint
    }
    return sum
}

fun search(arr: LongArray, target: Long, start: Int = 0, end: Int = arr.lastIndex): Int {
    var left = start
    var right = end
    while (left < right) {
        val mid = (left + right) / 2
        // mid 부터 end까지 돌려서 체크
        val sum = getFlattingDiffSum(arr, mid, arr.lastIndex)
        if (sum > target)
            left = mid + 1
        else
            right = mid
    }
    return right
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (M, N) = reader.readLine().split(" ").map { it.toLong() }
    val arr = LongArray(N.toInt()) { reader.readLine().toLong() }

    arr.sort()
    val idx = search(arr, M)
    val flattingCandyNeed = getFlattingDiffSum(arr, idx, arr.lastIndex)
    val candy = M - flattingCandyNeed
    // arr[idx] .. arr[N] 까지 arr[idx]로 취급
    val len = (N - idx)
    // 남은 사탕을 arr[idx]..arr[N]까지 균등하게 나눠줌
    val cnt = candy / len
    // 나머지들을 따로 -1처리해서 처리
    val mod = (candy % len).toInt()
    var sum = 0L
    for (i in 0 until idx) {
        sum += arr[i] * arr[i]
    }
    for (i in idx until idx + mod) {
        val num = arr[idx] - cnt - 1
        sum += num * num
    }
    for (i in idx +mod until N) {
        val num = arr[idx] - cnt
        sum += num * num
    }
    println(sum)
}