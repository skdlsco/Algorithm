package `3986`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    var cnt = 0
    repeat(N) {
        val stack = Stack<Char>()
        reader.readLine().forEach {
            if (stack.isNotEmpty() && stack.peek() == it) {
                stack.pop()
            } else
                stack.push(it)
        }
        if (stack.isEmpty())
            cnt++
    }
    println(cnt)
}