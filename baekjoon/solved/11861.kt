package `11861`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

data class Point(val len: Long, val height: Long)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val stringTokenizer = StringTokenizer(reader.readLine())
    val stack = Stack<Point>()
    var result = 0L
    repeat(N) {
        val now = stringTokenizer.nextToken().toLong()
        var len = 0L
        // stack에서 나보다 더 작아질 때까지 pop
        while (stack.isNotEmpty() && stack.peek().height >= now) {
            val top = stack.pop()
            len += top.len
            result = max(result, len * top.height)
        }
        len++
        result = max(result, now * len)
        stack.push(Point(len, now))
    }
    var len = 0L
    while (stack.isNotEmpty()) {
        val top = stack.pop()
        len += top.len
        result = max(result, len * top.height)
    }
    println(result)
}