package `10845`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class Queue {
    val list = LinkedList<Int>()

    fun push(num: Int) {
        list.addFirst(num)
    }

    fun pop(): Int {
        if (empty() == 1)
            return -1
        return list.removeLast()
    }

    fun size(): Int {
        return list.size
    }

    fun empty(): Int {
        return if (size() == 0) 1 else 0
    }

    fun front(): Int {
        if (empty() == 1)
            return -1
        return list.last()
    }

    fun back(): Int {
        if (empty() == 1)
            return -1
        return list.first()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val queue = Queue()
    repeat(N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        when (stringTokenizer.nextToken()) {
            "push" -> {
                val num = stringTokenizer.nextToken().toInt()
                queue.push(num)
            }

            "pop" -> {
                writer.write("${queue.pop()}\n")
            }

            "size" -> {
                writer.write("${queue.size()}\n")
            }

            "empty" -> {
                writer.write("${queue.empty()}\n")
            }

            "front" -> {
                writer.write("${queue.front()}\n")
            }

            "back" -> {
                writer.write("${queue.back()}\n")
            }
        }
    }
    writer.flush()
}