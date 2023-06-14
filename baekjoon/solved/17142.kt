package `17142`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun backTracking(
    combList: ArrayList<List<Pair<Int, Int>>>,
    M: Int,
    virusCellArr: ArrayList<Pair<Int, Int>>,
    comb: Array<Pair<Int, Int>>,
    cur: Int,
    depth: Int
) {
    if (depth == M) {
        combList.add(comb.toList())
        return
    }
    for (i in cur until virusCellArr.size) {
        comb[depth] = virusCellArr[i]
        backTracking(combList, M, virusCellArr, comb, i + 1, depth + 1)
    }
}

fun makeCombination(M: Int, virusCellArr: ArrayList<Pair<Int, Int>>): List<List<Pair<Int, Int>>> {
    val combinationList = ArrayList<List<Pair<Int, Int>>>()
    val comb = Array<Pair<Int, Int>>(M) { Pair(0, 0) }
    backTracking(combinationList, M, virusCellArr, comb, 0, 0)
    return combinationList
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { Array(N) { 0 } }
    var cellCount = 0
    val virusCellArr = ArrayList<Pair<Int, Int>>()
    for (y in 0 until N) {
        val row = reader.readLine().split(" ")
        for (x in 0 until N) {
            if (row[x] == "1")
                map[y][x] = 1
            else {
                if (row[x] == "2") {
                    virusCellArr.add(Pair(x, y))
                    map[y][x] = 2
                }
                cellCount++
            }
        }
    }
    var result = -1
    for (comb in makeCombination(M, virusCellArr)) {
        val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
        var count = virusCellArr.size
        val queue = LinkedList<Pair<Int, Pair<Int, Int>>>()
        comb.forEach {
            queue.add(Pair(0, it))
            visited[it.second][it.first] = true
        }
        var maxTurn = 0
        while (queue.isNotEmpty()) {
            val (turn, pos) = queue.pop()
            val (x, y) = pos
            maxTurn = maxOf(maxTurn, turn)
            if (count >= cellCount)
                break
            for (i in 0 until 4) {
                val nextX = x + dx[i]
                val nextY = y + dy[i]
                if (nextX in 0 until N && nextY in 0 until N &&
                    !visited[nextY][nextX] && map[nextY][nextX] != 1
                ) {
                    queue.add(Pair(turn + 1, Pair(nextX, nextY)))
                    visited[nextY][nextX] = true
                    if (map[nextY][nextX] == 0)
                        count++
                }
            }
        }
        while (queue.isNotEmpty())  {
            val (turn, _) = queue.pop()
            maxTurn = maxOf(maxTurn, turn)
        }
        if (count == cellCount) {
            if (result == -1)
                result = maxTurn
            else
                result = minOf(result, maxTurn)
        }
    }
    writer.write("${result}\n")
    writer.flush()
}