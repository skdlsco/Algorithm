package `17141`

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
    visited: Array<Boolean>,
    cur: Int,
    depth: Int
) {
    if (depth == M) {
        combList.add((visited.indices).filter { visited[it] }.map { virusCellArr[it] })
        return
    }
    for (i in cur until virusCellArr.size) {
        visited[i] = true
        backTracking(combList, M, virusCellArr, visited, i + 1, depth + 1)
        visited[i] = false
    }
}

fun makeCombination(M: Int, virusCellArr: ArrayList<Pair<Int, Int>>): List<List<Pair<Int, Int>>> {
    val combinationList = ArrayList<List<Pair<Int, Int>>>()
    val visited = Array<Boolean>(virusCellArr.size) { false }
    backTracking(combinationList, M, virusCellArr, visited, 0, 0)
    return combinationList
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val wallMap = Array<Array<Boolean>>(N) { Array(N) { false } }
    var cellCount = 0
    val virusCellArr = ArrayList<Pair<Int, Int>>()
    for (y in 0 until N) {
        val row = reader.readLine().split(" ")
        for (x in 0 until N) {
            if (row[x] == "1")
                wallMap[y][x] = true
            else {
                if (row[x] == "2")
                    virusCellArr.add(Pair(x, y))
                cellCount++
            }
        }
    }
    var result = -1
    for (comb in makeCombination(M, virusCellArr)) {
        val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
        var count = M
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
            for (i in 0 until 4) {
                val nextX = x + dx[i]
                val nextY = y + dy[i]
                if (nextX in 0 until N && nextY in 0 until N &&
                    !visited[nextY][nextX] && !wallMap[nextY][nextX]
                ) {
                    queue.add(Pair(turn + 1, Pair(nextX, nextY)))
                    visited[nextY][nextX] = true
                    count++
                }
            }
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