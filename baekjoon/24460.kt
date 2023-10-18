package `24460`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun getLuckyNumber(arr: Array<Array<Int>>, size: Int, x: Int, y: Int): Int {
    if (size == 1)
        return arr[y][x]
    val nextSize = size / 2
    val numbers = arrayOf(
        getLuckyNumber(arr, nextSize, x, y),
        getLuckyNumber(arr, nextSize, x + nextSize, y),
        getLuckyNumber(arr, nextSize, x, y + nextSize),
        getLuckyNumber(arr, nextSize, x + nextSize, y + nextSize)
    )
    numbers.sort()
    return numbers[1]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (x in 0 until N) {
            arr[y][x] = stringTokenizer.nextToken().toInt()
        }
    }
    val result = getLuckyNumber(arr, N, 0, 0)
    println(result)
}