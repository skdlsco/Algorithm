package `16236`

import java.util.*
import kotlin.collections.ArrayList

data class Point(var x: Int, var y: Int)

fun getSharkPosition(table: Array<Array<Int>>): Point {
    table.forEachIndexed { y, arr ->
        arr.forEachIndexed { x, n ->
            if (n == 9)
                return Point(x, y)
        }
    }
    return Point(0, 0)
}

fun getNearestFish(tableLength: Int, table: Array<Array<Int>>, startPos: Point, size: Int): Pair<Int, List<Point>> {
    val fishes = ArrayList<Point>()
    val visited = table.map {
        it.map {
            it > size
        }.toTypedArray()
    }.toTypedArray()
    var distance = 0
    visited[startPos.y][startPos.x] = true
    val nowQueue = LinkedList<Point>()
    val nextQueue = LinkedList<Point>()
    nextQueue.add(startPos)
    while (nextQueue.isNotEmpty()) {
        nowQueue.clear()
        nowQueue.addAll(nextQueue)
        nextQueue.clear()
        while (nowQueue.isNotEmpty()) {
            val node = nowQueue.pop()
            if (table[node.y][node.x] in 1 until size)
                fishes.add(node)
            if (node.x > 0 && !visited[node.y][node.x - 1]) {
                visited[node.y][node.x - 1] = true
                nextQueue.push(Point(node.x - 1, node.y))
            }
            if (node.x < tableLength - 1 && !visited[node.y][node.x + 1]) {
                visited[node.y][node.x + 1] = true
                nextQueue.push(Point(node.x + 1, node.y))
            }
            if (node.y > 0 && !visited[node.y - 1][node.x]) {
                visited[node.y - 1][node.x] = true
                nextQueue.push(Point(node.x, node.y - 1))
            }
            if (node.y < tableLength - 1 && !visited[node.y + 1][node.x]) {
                visited[node.y + 1][node.x] = true
                nextQueue.push(Point(node.x, node.y + 1))
            }
        }
        if (fishes.isNotEmpty())
            break
        distance++
    }
    return Pair(distance, fishes)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    var size = 2
    var need = 2
    val table = Array(N) {
        Array(N) {
            scanner.nextInt()
        }
    }
    var time = 0
    while (true) {
        val pos = getSharkPosition(table)
        val (distance, fishes) = getNearestFish(N, table, pos, size)
        if (distance == 0 || fishes.isEmpty())
            break
        val minY = fishes.minOf { it.y }
        val minX = fishes.filter { it.y == minY }.minOf { it.x }
        table[pos.y][pos.x] = 0
        table[minY][minX] = 9
        need--
        if (need <= 0 && size < 9) {
            size++
            need = size
        }
        time += distance
    }
    println(time)
}