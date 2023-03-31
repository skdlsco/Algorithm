package `17298`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val ngeArr = Array<Int>(N) { 0 }
    val stack = Stack<Int>()

    for (i in N - 1 downTo 0) {
        while (stack.isNotEmpty() && stack.peek() <= arr[i])
            stack.pop()
        if (stack.isEmpty())
            ngeArr[i] = -1
        else ngeArr[i] = stack.peek()
        stack.push(arr[i])
    }
    writer.write("${ngeArr.joinToString(" ")}\n")
    writer.flush()
}