package `17136`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

const val SIZE = 10
const val MAX = 26

// 색종이 size -> 0 ~ 4
fun check(square: Array<Array<Boolean>>, y: Int, x: Int, size: Int): Boolean {
    // 범위를 벗어나는 크기면 false
    if (y + size !in 0 until SIZE || x + size !in 0 until SIZE)
        return false
    for (y1 in y..y + size) {
        for (x1 in x..x + size) {
            if (!square[y1][x1])
                return false
        }
    }
    return true
}

fun put(square: Array<Array<Boolean>>, y: Int, x: Int, size: Int) {
    // 범위를 벗어나는 크기면 실패
    if (y + size !in 0 until SIZE || x + size !in 0 until SIZE)
        return
    for (y1 in y..y + size) {
        for (x1 in x..x + size) {
            square[y1][x1] = false
        }
    }
}

fun remove(square: Array<Array<Boolean>>, y: Int, x: Int, size: Int) {
    // 범위를 벗어나는 크기면 실패
    if (y + size !in 0 until SIZE || x + size !in 0 until SIZE)
        return
    for (y1 in y..y + size) {
        for (x1 in x..x + size) {
            square[y1][x1] = true
        }
    }
}

fun solve(square: Array<Array<Boolean>>, paper: Array<Int>, position: Int, cnt: Int): Int {
    var result = MAX
    for (i in position until SIZE * SIZE) {
        val y = i / SIZE
        val x = i % SIZE
        if (square[y][x]) {
            // 뭐라도 붙여야한다!
            // 다실패하면 return -1
            for (p in 0 until 5) {
                if (paper[p] > 0 && check(square, y, x, p)) {
                    put(square, y, x, p)
                    paper[p]--
                    val temp = solve(square, paper, i + 1, cnt + 1)
                    if (temp != -1)
                        result = min(temp, result)
                    remove(square, y, x, p)
                    paper[p]++
                }
            }
            return if (result == MAX)
                -1
            else
                result
        }
    }
    // 끝까지 붙여야 하는 칸이 없었다
    return cnt
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    // false -> 덮으면 안됨
    val square = Array<Array<Boolean>>(SIZE) { Array(SIZE) { false } }
    for (y in 0 until SIZE) {
        val line = reader.readLine().split(" ").map { it == "1" }
        for (x in 0 until SIZE) {
            square[y][x] = line[x]
        }
    }
    println(solve(square, arrayOf(5, 5, 5, 5, 5), 0, 0))
}