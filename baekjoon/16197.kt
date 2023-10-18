package `16197`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

data class Point(val x: Int, val y: Int) {
    fun isInMap(N: Int, M: Int): Boolean {
        return x in 0 until M && y in 0 until N
    }
}

data class Data(var coin1: Point, var coin2: Point, val depth: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(N) { Array(M) { ' ' } }
    var coin1Start = Point(-1, -1)
    var coin2Start = Point(-1, -1)
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            if (line[x] == 'o') {
                if (coin1Start.x == -1)
                    coin1Start = Point(x, y)
                else
                    coin2Start = Point(x, y)
            } else
                map[y][x] = line[x]
        }
    }

    val queue = LinkedList<Data>()
    queue.add(Data(coin1Start, coin2Start, 0))
    var result = -1
    while (queue.isNotEmpty() && result == -1) {
        val (coin1, coin2, depth) = queue.pop()
        if (depth == 10)
            continue
        for (i in 0 until 4) {
            val nextCoin1 = Point(coin1.x + dx[i], coin1.y + dy[i])
            val nextCoin2 = Point(coin2.x + dx[i], coin2.y + dy[i])
            var out = 0
            if (!nextCoin1.isInMap(N, M))
                out++
            if (!nextCoin2.isInMap(N, M))
                out++
            if (out == 1)
                result = depth + 1
            if (out == 2)
                continue
            val next = Data(nextCoin1, nextCoin2, depth + 1)
            if (!nextCoin1.isInMap(N, M) || map[nextCoin1.y][nextCoin1.x] == '#')
                next.coin1 = coin1
            if (!nextCoin2.isInMap(N, M) || map[nextCoin2.y][nextCoin2.x] == '#')
                next.coin2 = coin2
            queue.add(next)
        }

    }
    writer.write("${result}\n")
    writer.flush()
}