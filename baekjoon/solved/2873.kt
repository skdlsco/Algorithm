package baekjoon.solved

import java.io.BufferedReader
import java.io.InputStreamReader

data class Pos(val y: Int, val x: Int)

fun getXMinPos(map: Array<Array<Int>>): Pos {
    var minPos = Pos(0, 1)
    map.forEachIndexed { y, ints ->
        ints.forEachIndexed { x, value ->
            if ((y + x) % 2 == 1 && map[minPos.y][minPos.x] > value)
                minPos = Pos(y, x)
        }
    }
    return minPos
}

fun getEvenRoute(R: Int, C: Int, minPos: Pos): String {
    val builder = StringBuilder()
    var isPassedMinPos = false
    // 두 줄씩 구간을 잡음
    repeat(R / 2) {
        if (minPos.y / 2 == it) {
            // 구불 구불 한 구간 -> 최솟값이 존재하는 구간
            val destination = Pos(it * 2 + 1, C - 1)
            var nowPos = Pos(it * 2, 0)
            while (nowPos != destination) {
                if (nowPos.y < destination.y) {
                    // 아래로 내려가는 경우
                    // 아래가 최솟값인 경우
                    if (minPos.y == nowPos.y + 1 && minPos.x == nowPos.x)
                        isPassedMinPos = true
                    if (isPassedMinPos)
                        builder.append("RD")
                    else
                        builder.append("DR")
                    nowPos = Pos(nowPos.y + 1, nowPos.x + 1)
                } else {
                    // 위로 가는 경우
                    // 위가 최솟값인 경우
                    if (minPos.y == nowPos.y - 1 && minPos.x == nowPos.x)
                        isPassedMinPos = true
                    if (isPassedMinPos)
                        builder.append("RU")
                    else
                        builder.append("UR")
                    nowPos = Pos(nowPos.y - 1, nowPos.x + 1)
                }
            }
            isPassedMinPos = true
        } else {
            // 구간에 최솟 값이 없는 경우
            repeat(C - 1) {
                // 이미 통과한 경우 시작점이 반대편, L R이 바뀐다.
                if (isPassedMinPos)
                    builder.append('L')
                else
                    builder.append('R')
            }
            builder.append('D')
            repeat(C - 1) {
                if (isPassedMinPos)
                    builder.append('R')
                else
                    builder.append('L')
            }
        }
        if (it < R / 2 - 1)
            builder.append('D')
    }
    return builder.toString()
}

fun getOddRoute(R: Int, C: Int): String {
    // 홀수 라인 기준으로 RRDLL or DDRUU 반복
    val builder = StringBuilder()
    if (R % 2 == 1) {
        repeat(R) { y ->
            repeat(C - 1) { i ->
                if (y % 2 == 0)
                    builder.append('R')
                else
                    builder.append('L')
            }
            if (y < R - 1)
                builder.append('D')
        }
    } else {
        repeat(C) { x ->
            repeat(R - 1) { i ->
                if (x % 2 == 0)
                    builder.append('D')
                else
                    builder.append('U')
            }
            if (x < C - 1)
                builder.append('R')
        }
    }
    return builder.toString()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (R, C) = reader.readLine().split(" ").map { it.toInt() }
    val map: Array<Array<Int>> = Array(R) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    var route = ""
    if (R % 2 == 0 && C % 2 == 0) {
        // 짝수 경우 min 체크
        val xMinPos = getXMinPos(map)
        route = getEvenRoute(R, C, xMinPos)

    } else {
        route = getOddRoute(R, C)
    }
    println(route)
}
