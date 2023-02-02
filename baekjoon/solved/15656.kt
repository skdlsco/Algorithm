package `15656`

import java.io.BufferedWriter
import java.io.OutputStreamWriter

val writer = BufferedWriter(OutputStreamWriter(System.out))

fun f(N: Int, M: Int, numArr: Array<Int>, idxArr: Array<Int>, depth: Int) {
    if (depth == M) {
        repeat(M) {
            writer.write("${numArr[idxArr[it]]} ")
        }
        writer.write("\n")
        return
    }
    repeat(N) { i ->
        idxArr[depth] = i
        f(N, M, numArr, idxArr, depth + 1)
    }
}

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }
    val numArr = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    val idxArr = Array<Int>(N) { 0 }
    numArr.sort()
    f(N, M, numArr, idxArr, 0)

    writer.flush()
}