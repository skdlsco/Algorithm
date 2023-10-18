package `11003`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toInt()
    val L = stringTokenizer.nextToken().toInt()

    // idx, value
    val deque = ArrayDeque<IntArray>(N)
    stringTokenizer = StringTokenizer(reader.readLine())
    for (i in 0 until N) {
        val cur = stringTokenizer.nextToken().toInt()
        while (!deque.isEmpty() && deque.first()[1] >= cur)
            deque.removeFirst()
        deque.addFirst(intArrayOf(i, cur))
        while (!deque.isEmpty() && i - deque.last()[0] >= L)
            deque.removeLast()
        writer.write("${deque.last()[1]} ")
    }
    writer.flush()
}