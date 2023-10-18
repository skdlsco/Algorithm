package `9935`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val str = reader.readLine()
    val bomb = reader.readLine()
    val stack = Stack<Char>()

    str.forEach {
        stack.push(it)
        if (stack.size >= bomb.length) {
            for (i in bomb.indices) {
                if (stack[stack.size - bomb.length + i] != bomb[i])
                    break
                if (i == bomb.lastIndex) {
                    repeat(bomb.length) {
                        stack.pop()
                    }
                }
            }
        }
    }
    if (stack.isEmpty())
        println("FRULA")
    else
        println(stack.joinToString(""))
}