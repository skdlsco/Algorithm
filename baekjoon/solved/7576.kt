package `7576`
import java.util.*

class TomatoContainer(val width: Int, val height: Int, val originArr: Array<Array<Int>>) {
    private val nowQueue = LinkedList<Pair<Int, Int>>()
    private val nextQueue = LinkedList<Pair<Int, Int>>()
    var arr: Array<Array<Int>> = emptyArray()
    private fun initQueue() {
        nowQueue.clear()
        nextQueue.clear()
        arr.forEachIndexed { y, arr ->
            arr.forEachIndexed { x, v ->
                if (v == 1) {
                    nextQueue.add(Pair(x, y))
                }
            }
        }
    }

    private fun checkNext(x: Int, y: Int): Boolean {
        return arr[y][x] == 0
    }

    private fun addToNext(x: Int, y: Int) {
        arr[y][x] = 1
        nextQueue.push(Pair(x, y))
    }

    private fun checkFull(): Boolean {
        return arr.none() {
            it.any {
                it == 0
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
                val x = node.first;
                val y = node.second;
                if (x > 0 && checkNext(x - 1, y))
                    addToNext(x - 1, y)
                if (x < width - 1 && checkNext(x + 1, y))
                    addToNext(x + 1, y)
                if (y > 0 && checkNext(x, y - 1))
                    addToNext(x, y - 1)
                if (y < height - 1 && checkNext(x, y + 1))
                    addToNext(x, y + 1)
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
    val (N, M) = scanner.nextLine().split(" ").map { it.toInt() }
    val arr = (0 until M).map { y ->
        (0 until N).map { x ->
            scanner.nextInt()
        }.toTypedArray()
    }.toTypedArray()
    val container = TomatoContainer(N, M, arr)
    println(container.getResult())
}