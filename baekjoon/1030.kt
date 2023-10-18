package `1030`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// true white
// false black
fun check(s: Int, N: Int, K: Int, y: Int, x: Int): Boolean {
    if (s == 0)
        return true
    var cellSize = 1
    for (i in 1 until s)
        cellSize *= N
    val ny = y % cellSize
    val nx = x % cellSize
    val cy = y / cellSize
    val cx = x / cellSize

    // 5 3 -> 0~4, 1~3
    // 8 4 -> 0~7, 2~5
    val blackRange = if (K % 2 == 0) (N / 2) - (K / 2)..(N / 2) + (K / 2 - 1)
    else (N / 2) - (K / 2)..(N / 2) + (K / 2)
    if (cy in blackRange && cx in blackRange)
        return false
    return check(s - 1, N, K, ny, nx)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val tokens = reader.readLine().split(" ").map { it.toInt() }
    val s = tokens[0]
    val N = tokens[1]
    val K = tokens[2]
    val R1 = tokens[3]
    val R2 = tokens[4]
    val C1 = tokens[5]
    val C2 = tokens[6]
    // x,y cell의 색을 판별하자!
    // 전체 가로*세로 칸을 N * N등분 한다.
    // 해당 등분된 칸에 cell이 포함되어있는지 확인.
    // 해당 등분된 칸이 중앙 K*K인가? -> 검은색
    //                      아니다 -> 다음 단계 (좌표 조정)
    for (i in R1..R2) {
        for (j in C1..C2) {
            if (check(s, N, K, i, j))
                writer.write("0")
            else
                writer.write("1")
        }
        writer.newLine()
    }
    writer.flush()
}