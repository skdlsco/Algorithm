package `27939`

import java.io.BufferedReader
import java.io.InputStreamReader

val reader = BufferedReader(InputStreamReader(System.`in`))

fun getResult(kindArr: List<Char>): String {
    val (M, K) = reader.readLine().split(" ").map { it.toInt() }
    for (i in 0 until M) {
        val isWhiteAble = reader.readLine().split(" ").map { it.toInt() }.all {
            kindArr[it - 1] == 'W'
        }

        if (isWhiteAble) {
            return "W"
        }
    }
    return "P"
}

fun main() {
    val N = reader.readLine().toInt()
    val kindArr = reader.readLine().split(" ").map { it[0] }
    println(getResult(kindArr))
}