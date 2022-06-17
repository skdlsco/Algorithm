package c10026

import java.util.*

data class Point(val x: Int, val y: Int)

fun toNext(
    visited: Array<Array<Boolean>>,
    arr: Array<Array<Char>>,
    queue: Queue<Point>,
    isDisable: Boolean,
    color: Char,
    pos: Point
) {
    if (pos.y in arr.indices && pos.x in arr.first().indices && !visited[pos.y][pos.x]) {
        val cell = arr[pos.y][pos.x]
        if ((isDisable && color in "RG" && cell in "RG") || color == cell) {
            visited[pos.y][pos.x] = true
            queue.add(pos)
        }
    }
}

fun checkArea(visited: Array<Array<Boolean>>, arr: Array<Array<Char>>, start: Point, isDisable: Boolean) {
    val queue = LinkedList<Point>()
    val color = arr[start.y][start.x]
    queue.push(start)
    while (queue.isNotEmpty()) {
        val node = queue.pop()
        toNext(visited, arr, queue, isDisable, color, Point(node.x + 1, node.y))
        toNext(visited, arr, queue, isDisable, color, Point(node.x - 1, node.y))
        toNext(visited, arr, queue, isDisable, color, Point(node.x, node.y + 1))
        toNext(visited, arr, queue, isDisable, color, Point(node.x, node.y - 1))
    }
}

fun getNextPos(visited: Array<Array<Boolean>>): Point {
    visited.forEachIndexed { y, booleans ->
        booleans.forEachIndexed { x, v ->
            if (!v)
                return Point(x, y)
        }
    }
    return Point(0, 0)
}

fun getAreaCnt(N: Int, arr: Array<Array<Char>>, isDisable: Boolean): Int {
    val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
    var cnt = 0
    var nextPos = getNextPos(visited)
    while (!visited[nextPos.y][nextPos.x]) {
        visited[nextPos.y][nextPos.x] = true
        checkArea(visited, arr, nextPos, isDisable)
        nextPos = getNextPos(visited)
        cnt++
    }
    return cnt
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextLine().toInt()
    val arr = Array<Array<Char>>(N) {
        scanner.nextLine().toCharArray().toTypedArray()
    }
    println("${getAreaCnt(N, arr, false)} ${getAreaCnt(N, arr, true)}")
}