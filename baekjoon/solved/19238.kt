package `19238`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val EMPTY = 0
val WALL = -1

data class Pos(val x: Int = 0, val y: Int = 0) {
    constructor(list: List<Int>) : this(list[1], list[0])
}

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun findPassenger(N: Int, texiPos: Pos, map: Array<Array<Int>>): Pair<Int, Int> {
    // 승객 찾기
    val queue = LinkedList<Pair<Int, Pos>>()
    val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
    queue.add(Pair(0, texiPos))
    visited[texiPos.y][texiPos.x] = true
    var findLength = -1
    var nextPassenger = Pos(1000, 1000)
    var nextPassengerIdx = -1
    while (queue.isNotEmpty()) {
        val (len, pos) = queue.pop()
        if (findLength != -1 && len > findLength)
            break
        if (map[pos.y][pos.x] > 0) {
            if (nextPassenger.y > pos.y || (nextPassenger.y == pos.y && nextPassenger.x > pos.x)) {
                nextPassenger = pos
                nextPassengerIdx = map[pos.y][pos.x]
            }
            findLength = len
        }
        for (i in 0 until 4) {
            val nx = pos.x + dx[i]
            val ny = pos.y + dy[i]
            if (nx !in 0 until N || ny !in 0 until N)
                continue
            if (map[ny][nx] != WALL && !visited[ny][nx]) {
                queue.add(Pair(len + 1, Pos(nx, ny)))
                visited[ny][nx] = true
            }
        }
    }
    return Pair(findLength, nextPassengerIdx)
}

fun solve(N: Int, M: Int, sOil: Int, sTexiPos: Pos, map: Array<Array<Int>>, passengerInfos: List<Pair<Pos, Pos>>): Int {
    var texiPos = sTexiPos
    var curOil = sOil
    for (i in 0 until M) {
        val (len, idx) = findPassenger(N, texiPos, map)
        if (idx == -1)
            return -1
        curOil -= len
        if (curOil < 0)
            return -1
        val passengerInfo = passengerInfos[idx]
        texiPos = passengerInfo.first
        map[passengerInfo.first.y][passengerInfo.first.x] = 0
        val destination = passengerInfo.second
        val queue = LinkedList<Pair<Int, Pos>>()
        val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
        queue.add(Pair(0, texiPos))
        visited[texiPos.y][texiPos.x] = true
        var findLen = -1
        while (queue.isNotEmpty()) {
            val (len, pos) = queue.pop()
            if (pos == destination) {
                findLen = len
                break
            }
            for (i in 0 until 4) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if (nx !in 0 until N || ny !in 0 until N)
                    continue
                if (map[ny][nx] != WALL && !visited[ny][nx]) {
                    queue.add(Pair(len + 1, Pos(nx, ny)))
                    visited[ny][nx] = true
                }
            }
        }
        if (findLen == -1)
            return -1
        curOil -= findLen
        if (curOil < 0)
            return -1
        curOil += findLen * 2
        texiPos = passengerInfo.second
    }
    return curOil
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, sOil) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            if (row[x] == 1)
                map[y][x] = -1
        }
    }
    val texiPos = Pos(reader.readLine().split(" ").map { it.toInt() - 1 })
    val passengerInfos = ArrayList<Pair<Pos, Pos>>()
    passengerInfos.add(Pair(Pos(), Pos()))
    repeat(M) {
        val (py, px, dy, dx) = reader.readLine().split(" ").map { it.toInt() - 1 }
        map[py][px] = it + 1
        passengerInfos.add(Pair(Pos(px, py), Pos(dx, dy)))
    }
    val ans = solve(N, M, sOil, texiPos, map, passengerInfos)
    if (ans < 0)
        writer.write("-1\n")
    else
        writer.write("${ans}\n")
    writer.flush()
}