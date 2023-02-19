package `5397`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    // 커서를 기준으로 왼쪽 오른쪽
    repeat(T) {
        val left = Stack<Char>()
        val right = Stack<Char>()
        val input = reader.readLine()
        input.forEach {
            when (it) {
                '<' -> {
                    if (left.isNotEmpty())
                        right.push(left.pop())
                }

                '>' -> {
                    if (right.isNotEmpty())
                        left.push(right.pop())
                }

                '-' -> {
                    if (left.isNotEmpty())
                        left.pop()
                }

                else -> {
                    left.push(it)
                }
            }
        }
        writer.write(left.joinToString(""))
        // 커서 오른쪽은 출력시 뒤집어서
        writer.write(right.reversed().joinToString(""))
        writer.newLine()
    }
    writer.flush()
}