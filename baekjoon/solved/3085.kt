package `3085`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun check(size: Int, map: Array<Array<Char>>): Int {
    var result = 0
    repeat(size) { y ->
        var prev = map[y][0]
        var cnt = 1
        for (x in 1 until size) {
            if (prev == map[y][x]) {
                cnt++
                result = max(result, cnt)
            } else {
                cnt = 1
            }
            prev = map[y][x]
        }
    }
    repeat(size) { x ->
        var prev = map[0][x]
        var cnt = 1
        for (y in 1 until size) {
            if (prev == map[y][x]) {
                cnt++
                result = max(result, cnt)
            } else {
                cnt = 1
            }
            prev = map[y][x]
        }
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val map = Array<Array<Char>>(N) { reader.readLine().toCharArray().toTypedArray() }
    var maxValue = 0

    repeat(N) { y ->
        repeat(N) { x ->
            if (x + 1 < N && map[y][x + 1] != map[y][x]) {
                val temp = map[y][x]
                map[y][x] = map[y][x + 1]
                map[y][x + 1] = temp
                maxValue = max(check(N, map), maxValue)
                map[y][x + 1] = map[y][x]
                map[y][x] = temp
            }
            if (y + 1 < N && map[y + 1][x] != map[y][x]) {
                val temp = map[y][x]
                map[y][x] = map[y + 1][x]
                map[y + 1][x] = temp
                maxValue = max(check(N, map), maxValue)
                map[y + 1][x] = map[y][x]
                map[y][x] = temp
            }
        }
    }
    println(maxValue)
}