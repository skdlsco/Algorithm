package `3158`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    // 4로 끝날 때 -> 주위 3개
    // 1,2,3 일 때 해당 단계의 4를 만난다.
    // 전에 방문한 숫자를 만나게 되면? -> 고립돼서 이후 단계들을 안만나게된다
    val T = reader.readLine()
    val visited = Array<Boolean>(4) { false }
    if (T.endsWith("4")) {
        val prefix = T.substring(0 until T.length - 1)
        println("${prefix}1")
        println("${prefix}2")
        println("${prefix}3")
    } else {
        for (i in T.length - 1 downTo 1) {
            if (!visited[T[i].digitToInt()]) {
                println("${T.substring(0 until i)}4")
                visited[T[i].digitToInt()] = true
            }
        }
    }
}