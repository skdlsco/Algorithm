package `2812`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (_, K) = reader.readLine().split(" ").map { it.toInt() }
    val line = reader.readLine()
    val stack = Stack<Char>()
    var cnt = 0
    // 왼쪽에 있는 수가 커야 가장 큰 수
    line.forEach {
        while (cnt < K && stack.isNotEmpty() && stack.peek() < it) {
            stack.pop()
            cnt++
        }
        stack.push(it)
    }
    while (cnt < K) {
        stack.pop()
        cnt++
    }
    println(stack.joinToString(""))
}