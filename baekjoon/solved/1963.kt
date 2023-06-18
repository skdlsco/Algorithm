package `1963`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val primes = BooleanArray(10000) { true }
    primes[0] = false
    primes[1] = false
    for (i in 2 until 100) {
        for (j in i + i until 10000 step i) {
            primes[j] = false
        }
    }
    repeat(T) {
        val board = primes.clone()
        val (start, end) = reader.readLine().split(" ").map { it.toInt() }
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(Pair(start, 0))
        var result = -1
        board[start] = false
        while (queue.isNotEmpty()) {
            val (num, depth) = queue.pop()
            if (num == end) {
                result = depth
                break
            }
            var digits = 1
            for (i in 0 until 4) {
                for (j in 0 until 10) {
                    val next = num - (((num / digits) % 10) * digits) + (j * digits)
                    if (next >= 1000 && board[next]) {
                        board[next] = false
                        queue.add(Pair(next, depth + 1))
                    }
                }
                digits *= 10
            }
        }
        if (result == -1)
            writer.write("Impossible\n")
        else
            writer.write("${result}\n")
    }
    writer.flush()
}