package `7662`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap

class `7662` {
    val minQueue = PriorityQueue<Int>()
    val maxQueue = PriorityQueue<Int>(kotlin.Comparator { o1, o2 -> if (o2 > o1) 1 else -1 })
    val map = HashMap<Int, Int>()

    fun insert(n: Int) {
        maxQueue.add(n)
        minQueue.add(n)
        map[n] = (map[n] ?: 0) + 1
    }

    fun getMax(): Int? {
        while (maxQueue.isNotEmpty()) {
            val r = maxQueue.remove()
            if (map[r] != 0) {
                map[r] = map[r]!! - 1
                return r
            }
        }
        return null
    }

    fun getMin(): Int? {
        while (minQueue.isNotEmpty()) {
            val r = minQueue.remove()
            if (map[r] != 0) {
                map[r] = map[r]!! - 1
                return r
            }
        }
        return null
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val k = reader.readLine().toInt()
        val queue = `7662`()
        repeat(k) {
            val (command, n) = reader.readLine()!!.split(" ")
            when (command) {
                "I" -> queue.insert(n.toInt())
                "D" -> {
                    when (n.toInt()) {
                        -1 -> queue.getMin()
                        1 -> queue.getMax()
                    }
                }
            }
        }
        val max = queue.getMax()
        val min = queue.getMin()
        if (max == null && min == null)
            writer.write("EMPTY\n")
        else
            writer.write("$max ${min ?: max}\n")
    }
    writer.flush()
}