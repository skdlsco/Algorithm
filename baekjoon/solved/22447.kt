package `22447`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(1000001) { false }
    val customerArr = reader.readLine().split(" ").map { it.toInt() }
    var last = 0
    var cup = 0
    customerArr.forEach { t ->
        var cnt = 0
        // t - M 까지 cup 제작
        // 마지막으로 컵 제작 한 부분 부터 시작하자
        for (i in last until t - M) {
            if (!visited[i])
                cup++
            visited[i] = true
        }
        val start = max(t - M, last)
        for (i in start until t) {
            if (visited[i])
                continue
            else {
                visited[i] = true
                if (cup == 0) {
                    cup++
                    last = i + 1
                } else  {
                    last = i + 1
                    cnt++
                    cup--
                    break
                }
            }
        }
        if (cnt < 1) {
            println("fail")
            return
        }
        visited[t] = true
    }
    println("success")
}