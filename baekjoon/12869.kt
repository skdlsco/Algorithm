package `12869`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

val table = listOf(listOf(9, 3, 1), listOf(9, 1, 3), listOf(3, 9, 1), listOf(3, 1, 9), listOf(1, 9, 3), listOf(1, 3, 9))
val dp = Array<Array<Array<Int>>>(61) { Array(61) { Array(61) { Int.MAX_VALUE } } }

fun getResult(scv1: Int, scv2: Int, scv3: Int, cnt: Int) {
    if (dp[scv1][scv2][scv3] <= cnt)
        return
    dp[scv1][scv2][scv3] = cnt
    table.forEach {
        getResult(max(scv1 - it[0], 0), max(scv2 - it[1], 0), max(scv3 - it[2], 0), cnt + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val N = reader.readLine().toInt()
    val scvList = Array<Int>(3) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { index, i ->
        scvList[index] = i
    }
    getResult(scvList[0], scvList[1], scvList[2], 0)
    writer.write("${dp[0][0][0]}")
    writer.flush()
    writer.close()
    reader.close()
}