package `27979`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val weightArr = reader.readLine().split(" ").map { it.toInt() }
    val stack = Stack<Int>()
    var cnt = 0
    var ball = 0
    weightArr.reversed().forEach {
        while (stack.isNotEmpty() && stack.peek() < it) {
            ball = max(ball, stack.pop())
            cnt++
        }
        stack.push(it)
    }
    while (stack.isNotEmpty() && stack.peek() < ball) {
        stack.pop()
        cnt++
    }
    writer.write("${cnt}")
    writer.flush()
}