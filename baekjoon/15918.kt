package `15918`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun getAnswer(n: Int, usedArr: BooleanArray, arr: IntArray, idx: Int): Int {
    if (idx == n * 2) {
        return 1
    }
    if (arr[idx] != 0)
        return getAnswer(n, usedArr, arr, idx + 1)
    var cnt = 0
    for (num in 1..n) {
        if (!usedArr[num] && num + idx + 1 < 2 * n + 1 && arr[num + idx + 1] == 0) {
            usedArr[num] = true
            arr[idx] = num
            arr[num + idx + 1] = num
            cnt += getAnswer(n, usedArr, arr, idx + 1)
            usedArr[num] = false
            arr[idx] = 0
            arr[num + idx + 1] = 0
        }
    }
    return cnt
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, x, y) = reader.readLine().split(" ").map { it.toInt() }

    val usedArr = BooleanArray(n + 1) { false }
    val arr = IntArray(n * 2 + 1) { 0 }
    val fix = (y - x) - 1
    usedArr[fix] = true
    arr[x] = fix
    arr[y] = fix
    println(getAnswer(n, usedArr, arr, 1))
}