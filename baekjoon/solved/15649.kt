package `15649`

import java.io.BufferedWriter
import java.io.OutputStreamWriter

val writer = BufferedWriter(OutputStreamWriter(System.out))

fun f(N: Int, M: Int, depth: Int, arr: Array<Int>) {
    if (depth == M) {
        writer.write("${arr.joinToString(" ")}\n")
        return
    }
    for (i in 1..N) {
        val isExist = (0 until depth).any {
            arr[it] == i
        }
        if (!isExist) {
            arr[depth] = i
            f(N, M, depth + 1, arr)
        }
    }
}

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array<Int>(M) { 0 }
    f(N, M, 0, arr)
    writer.flush()
}