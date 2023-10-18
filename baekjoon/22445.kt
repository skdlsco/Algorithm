package `22445`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun checkCanExist(map: Array<Array<Boolean>>, distance: Int, N: Int, M: Int, y: Int, x: Int) {
    for (gy in 0 until N) {
        for (gx in 0 until M) {
            if (abs(gy - y) + abs(gx - x) != distance)
                map[gy][gx] = false
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Boolean>>(N) { Array(M) { true } }

    repeat(N - 1) {
        val distance = reader.readLine().toInt()
        checkCanExist(map, distance, N, M, it, 0)
    }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { x, distance ->
        checkCanExist(map, distance, N, M, N - 1, x)
    }
    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x]) {
                println("${y + 1} ${x + 1}")
                return
            }
        }
    }
}