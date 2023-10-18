package `27969`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val components = reader.readLine().split(" ")
    val stack = Stack<Int>()
    components.forEach {
        if (it == "[") {
            stack.add(0)
        } else if (it == "]") {
            val now = stack.pop() + 8
            if (stack.isEmpty())
                stack.add(now)
            else {
                stack.add(stack.pop() + now)
            }
        } else if (it.first() in "1234567890") {
            stack.add(stack.pop() + 8)
        } else {
            stack.add(stack.pop() + it.length + 12)
        }
    }
    println(stack.pop())
}