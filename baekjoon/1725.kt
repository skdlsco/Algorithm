package `1725`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

data class Point(val len: Int, val height: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val stack = Stack<Point>()
    val arr = Array<Int>(N + 1) { 0 }
    var result = 0
    repeat(N) {
        arr[it] = reader.readLine().toInt()
    }
    // 맨 마지막이 0으로 설정해두면 마지막에 기존 stack을 전부 비울 수 있다
    arr.forEach { now ->
        var len = 0
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
    println(result)
}