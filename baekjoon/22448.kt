package `22448`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, F) = reader.readLine().split(" ").map { it.toInt() }
    val wood = Array<Array<Boolean>>(N) { Array(M) { false } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            wood[y][x] = line[x] == '#'
        }
    }
    var sum = 0
    // 가로 방향 먼저
    for (y in 0 until N - 1) {
        var length = 0
        // 직진 못하는 구간 이전 끝 부분 -> 이게 존재 하는 경우 sum += length
        // 아닌 경우 -> F와 비교
        var isWoodExist = false
        for (x in 0 until M) {
            if (wood[y][x] && wood[y + 1][x]) {
                // 위 아래 둘다 나무 인경우(직진 못함)

                // 뒤에 못자르는 구간이 없고 length가 F보다 큰 경우
                if (length > 0)
                    sum += if (!isWoodExist && length > F) F else length
                isWoodExist = true
                length = 0
            } else if (wood[y][x] xor wood[y + 1][x]) {
                // 둘중 하나만 나무 인경우(잘라야함)
                length++
            }
        }
        // length 남아있으면 그냥 쭉 자르면 된다.
        if (length > 0)
            sum += if (length > F) F else length
    }
    // 세로 방향으로 반복
    // 가로 방향 먼저
    for (x in 0 until M - 1) {
        var length = 0
        var isWoodExist = false
        for (y in 0 until N) {
            if (wood[y][x] && wood[y][x + 1]) {
                if (length > 0)
                    sum += if (!isWoodExist && length > F) F else length
                isWoodExist = true
                length = 0
            } else if (wood[y][x] xor wood[y][x + 1]) {
                length++
            }
        }
        if (length > 0)
            sum += if (length > F) F else length
    }
    println(sum)
}