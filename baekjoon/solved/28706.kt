package `28706`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        var state = Array<Boolean>(7) { false }
        var nextState = Array<Boolean>(7) { false }
        state[1] = true
        repeat(N) {
            for (i in 0 until 7)
                nextState[i] = false
            val (op1, v1, op2, v2) = reader.readLine().split(" ")
            for (i in 0 until 7) {
                if (state[i]) {
                    if (op1 == "+")
                        nextState[(i + v1.toInt()) % 7] = true
                    if (op1 == "*")
                        nextState[(i * v1.toInt()) % 7] = true
                    if (op2 == "+")
                        nextState[(i + v2.toInt()) % 7] = true
                    if (op2 == "*")
                        nextState[(i * v2.toInt()) % 7] = true
                }
            }
            for (i in 0 until 7) {
                state[i] = nextState[i]
            }
        }
        if (state[0])
            writer.write("LUCKY")
        else
            writer.write("UNLUCKY")
        writer.newLine()
    }
    writer.flush()
}