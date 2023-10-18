package `7569`

import java.util.*


data class Point(val x: Int, val y: Int, val z: Int) {

}

class TomatoContainer(val size: Point, val originArr: Array<Array<Array<Int>>>) {
    private val nowQueue = LinkedList<Point>()
    private val nextQueue = LinkedList<Point>()
    var arr: Array<Array<Array<Int>>> = emptyArray()
    private fun initQueue() {
        nowQueue.clear()
        nextQueue.clear()
        arr.forEachIndexed { z, arr ->
            arr.forEachIndexed { y, arr ->
                arr.forEachIndexed { x, v ->
                    if (v == 1) {
                        nextQueue.add(Point(x, y, z))
                    }
                }
            }
        }

    }

    private fun checkNext(x: Int, y: Int, z: Int): Boolean {
        return arr[z][y][x] == 0
    }

    private fun addToNext(x: Int, y: Int, z: Int) {
        arr[z][y][x] = 1
        nextQueue.push(Point(x, y, z))
    }

    private fun checkFull(): Boolean {
        return arr.none {
            it.any {
                it.any {
                    it == 0
                }
            }
        }
    }

    fun getResult(): Int {
        arr = originArr
        initQueue()
        var cnt = -1
        while (nextQueue.isNotEmpty()) {
            nowQueue.addAll(nextQueue)
            nextQueue.clear()
            while (nowQueue.isNotEmpty()) {
                val node = nowQueue.pop()
                val x = node.x
                val y = node.y
                val z = node.z
                if (x > 0 && checkNext(x - 1, y, z))
                    addToNext(x - 1, y, z)
                if (x < size.x - 1 && checkNext(x + 1, y, z))
                    addToNext(x + 1, y, z)
                if (y > 0 && checkNext(x, y - 1, z))
                    addToNext(x, y - 1, z)
                if (y < size.y - 1 && checkNext(x, y + 1, z))
                    addToNext(x, y + 1, z)
                if (z > 0 && checkNext(x, y, z - 1))
                    addToNext(x, y, z - 1)
                if (z < size.z - 1 && checkNext(x, y, z + 1))
                    addToNext(x, y, z + 1)
            }
            cnt++
        }
        return if (checkFull())
            cnt
        else -1
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, M, H) = scanner.nextLine().split(" ").map { it.toInt() }
    val arr = (0 until H).map { h ->
        (0 until M).map { y ->
            (0 until N).map { x ->
                scanner.nextInt()
            }.toTypedArray()
        }.toTypedArray()
    }.toTypedArray()
    val container = TomatoContainer(Point(N, M, H), arr)
    println(container.getResult())
}