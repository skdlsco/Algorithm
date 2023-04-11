package baekjoon.solved

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

class Solution {
    class Deque {
        private var size = 0
        private var head: Node<Int>? = null
        private var tail: Node<Int>? = null

        fun pushFront(x: Int) {
            val cur = Node(x)
            if (isEmpty()) {
                head = cur
                tail = cur
            } else {
                head!!.prev = cur
                cur.next = head
                head = cur
            }
            size++
        }

        fun pushBack(x: Int) {
            val cur = Node(x)
            if (isEmpty()) {
                head = cur
                tail = cur
            } else {
                tail!!.next = cur
                cur.prev = tail
                tail = cur
            }
            size++
        }

        fun popFront(): Int {
            if (isEmpty())
                return -1
            val result = head!!.data
            if (size == 1) {
                head = null
                tail = null
            } else {
                head!!.next!!.prev = null
                head = head!!.next
            }
            size--
            return result
        }

        fun popBack(): Int {
            if (isEmpty())
                return -1
            val result = tail!!.data
            if (size == 1) {
                head = null
                tail = null
            } else {
                tail!!.prev!!.next = null
                tail = tail!!.prev
            }
            size--
            return result
        }

        fun size(): Int {
            return size
        }

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun front(): Int {
            if (isEmpty())
                return -1
            return head!!.data
        }

        fun back(): Int {
            if (isEmpty())
                return -1
            return tail!!.data
        }
    }

    class Node<T>(var data: T, var prev: Node<T>? = null, var next: Node<T>? = null)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val deque = Solution.Deque()
    repeat(N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val command = stringTokenizer.nextToken()
        when (command) {
            "push_back" -> {
                val data = stringTokenizer.nextToken().toInt()
                deque.pushBack(data)
            }

            "push_front" -> {
                val data = stringTokenizer.nextToken().toInt()
                deque.pushFront(data)
            }

            "front" -> {
                writer.write("${deque.front()}\n")
            }

            "back" -> {
                writer.write("${deque.back()}\n")
            }

            "size" -> {
                writer.write("${deque.size()}\n")
            }

            "empty" -> {
                writer.write("${if (deque.isEmpty()) 1 else 0}\n")
            }

            "pop_front" -> {
                writer.write("${deque.popFront()}\n")
            }

            "pop_back" -> {
                writer.write("${deque.popBack()}\n")
            }
        }
    }
    writer.flush()
}