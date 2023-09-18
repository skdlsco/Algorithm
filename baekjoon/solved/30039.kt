package `30039`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Queueueue() {
    val top = ArrayDeque<Int>()
    val bottom = ArrayDeque<Int>()
    val left = ArrayDeque<Int>()
    val right = ArrayDeque<Int>()
    var share: Int? = null

    fun hpush(x: Int) {
        if (share == null) {
            share = x
            return
        }
        right.addLast(x)
        if (right.size - left.size > 1) {
            left.addLast(share!!)
            share = right.removeFirst()
        }
    }

    fun hpop(): Int {
        if (empty() == 1)
            return -1
        if (hsize() == 1) {
            val temp = share!!
            share = null
            if (bottom.isNotEmpty()) {
                share = bottom.removeLast()
                if (top.size - bottom.size > 1) {
                    bottom.addLast(share!!)
                    share = top.removeFirst()
                }
            } else if (top.isNotEmpty())
                share = top.removeFirst()
            return temp
        }
        if (left.size == 0) {
            val temp = share!!
            share = right.removeFirst()
            return temp
        }
        val temp = left.removeFirst()
        if (right.size - left.size > 1) {
            left.addLast(share!!)
            share = right.removeFirst()
        }
        return temp
    }

    fun hfront(): Int {
        return if (left.isNotEmpty())
            left.first()
        else share ?: -1
    }

    fun hback(): Int {
        return if (right.isNotEmpty())
            right.last()
        else share ?: -1
    }

    fun hsize(): Int {
        return left.size + right.size + if (share == null) 0 else 1
    }

    fun vpush(x: Int) {
        if (share == null) {
            share = x
            return
        }
        top.addLast(x)
        if (top.size - bottom.size > 1) {
            bottom.addLast(share!!)
            share = top.removeFirst()
        }
    }

    fun vpop(): Int {
        if (empty() == 1)
            return -1
        if (vsize() == 1) {
            val temp = share!!
            share = null
            if (left.isNotEmpty()) {
                share = left.removeLast()
                if (right.size - left.size > 1) {
                    left.addLast(share!!)
                    share = right.removeFirst()
                }
            } else if (right.isNotEmpty())
                share = right.removeFirst()
            return temp
        }
        if (bottom.size == 0) {
            val temp = share!!
            share = top.removeFirst()
            return temp
        }
        val temp = bottom.removeFirst()
        if (top.size - bottom.size > 1) {
            bottom.addLast(share!!)
            share = top.removeFirst()
        }
        return temp
    }

    fun vfront(): Int {
        return if (bottom.isNotEmpty())
            bottom.first()
        else share ?: -1
    }

    fun vback(): Int {
        return if (top.isNotEmpty())
            top.last()
        else share ?: -1
    }

    fun vsize(): Int {
        return bottom.size + top.size + if (share == null) 0 else 1
    }

    fun empty(): Int {
        if (size() == 0)
            return 1
        else return 0
    }

    fun size(): Int {
        return top.size + bottom.size + left.size + right.size + if (share == null) 0 else 1
    }

    fun middle(): Int {
        return share ?: -1
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val queue = Queueueue()
    repeat(N) {
        val input = reader.readLine().split(" ")
        when (input[0]) {
            "hpush" -> queue.hpush(input[1].toInt())
            "hpop" -> writer.write("${queue.hpop()}\n")
            "hfront" -> writer.write("${queue.hfront()}\n")
            "hback" -> writer.write("${queue.hback()}\n")
            "hsize" -> writer.write("${queue.hsize()}\n")
            "vpush" -> queue.vpush(input[1].toInt())
            "vpop" -> writer.write("${queue.vpop()}\n")
            "vfront" -> writer.write("${queue.vfront()}\n")
            "vback" -> writer.write("${queue.vback()}\n")
            "vsize" -> writer.write("${queue.vsize()}\n")
            "size" -> writer.write("${queue.size()}\n")
            "empty" -> writer.write("${queue.empty()}\n")
            "middle" -> writer.write("${queue.middle()}\n")

        }
    }
    writer.flush()
}
    