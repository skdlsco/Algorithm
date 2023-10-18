package `3055`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

data class Point(val y: Int, val x: Int)
enum class Type {
    WATER,
    HEDGEHOG
}

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

data class Object(val type: Type, val p: Point, val dist: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (R, C) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Boolean>>(R) { Array(C) { false } }
    val visited = Array<Array<Boolean>>(R) { Array(C) { false } }
    val queue = LinkedList<Object>()
    var start = Point(0, 0)
    var dest = Point(0, 0)
    for (y in 0 until R) {
        val line = reader.readLine()
        for (x in 0 until C) {
            when (line[x]) {
                'S' -> start = Point(y, x)
                'D' -> dest = Point(y, x)
                'X' -> {
                    map[y][x] = true
                }
                '*' -> {
                    map[y][x] = true
                    queue.add(Object(Type.WATER, Point(y, x), 0))
                }
            }
        }
    }
    visited[start.y][start.x] = true
    queue.add(Object(Type.HEDGEHOG, start, 0))
    var result: Int? = null
    while (queue.isNotEmpty()) {
        val now = queue.pop()
        if (now.type == Type.HEDGEHOG &&
                now.p.y == dest.y && now.p.x == dest.x) {
            result = now.dist
            break
        }
        repeat(4) {
            val nextY = now.p.y + dy[it]
            val nextX = now.p.x + dx[it]
            if (nextY in 0 until R && nextX in 0 until C) {
                when (now.type) {
                    Type.WATER -> {
                        if (!map[nextY][nextX] && (dest.y != nextY || dest.x != nextX)) {
                            map[nextY][nextX] = true
                            queue.add(Object(now.type, Point(nextY, nextX), now.dist + 1))
                        }
                    }
                    Type.HEDGEHOG -> {
                        if (!map[nextY][nextX] && !visited[nextY][nextX]) {
                            visited[nextY][nextX] = true
                            queue.add(Object(now.type, Point(nextY, nextX), now.dist + 1))
                        }
                    }
                }
            }
        }
    }
    if (result == null)
        println("KAKTUS")
    else
        println(result)
}