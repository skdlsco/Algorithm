package `29715`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val (X, Y) = reader.readLine().split(" ").map { it.toInt() }
    var posCnt = 0
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        if (a != 0)
            posCnt++
    }
    val real = N - posCnt
    var cnt = 1
    for (i in 0 until M - posCnt) {
        cnt *= real - i
    }
    for (i in 0 until N - M) {
        cnt *= (9 - M - i)
    }
    val inputTime = cnt * X
    val errorCnt = cnt / 3 - if (cnt % 3 == 0) 1 else 0
    val errorTime = errorCnt * Y
    val sum = inputTime + errorTime
    writer.write("${sum}\n")
    writer.flush()
}
    