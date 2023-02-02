package `15651`

import java.io.BufferedWriter
import java.io.OutputStreamWriter

val writer = BufferedWriter(OutputStreamWriter(System.out))

fun f(N: Int, M: Int, depth: Int, arr: Array<Int>) {
    if (depth == M) {
        repeat(M) {
            writer.write("${arr[it]} ")
        }
        writer.write("\n")
        return
    }
    for (i in 1..N) {
        arr[depth] = i
        f(N, M, depth + 1, arr)
    }
}

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array<Int>(N) { 0 }
    f(N, M, 0, arr)
    writer.flush()
}