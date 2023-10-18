package B

import java.io.BufferedReader
import java.io.InputStreamReader

fun getNextStart(existArr: Array<Boolean>, start: Int): Int {
    for (i in start until existArr.size) {
        if (!existArr[i]) {
            return i
        }
    }
    return -1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().trim().split(" ").map { it.toInt() }
    val existArr = Array<Boolean>(N) { false }
    reader.readLine().trim().split(" ").map { it.toInt() - 1 }.forEach {
        existArr[it] = true
    }
    // 세칸 연속으로 비면새로 시작하기
    var sum = 0
    var length = 0
    for (i in 0 until N) {
        if (existArr[i])
            continue
        // 이전에 언제 나왔지?
        var prev = i - 1
        for (j in i - 1 downTo 0) {
            if (!existArr[j]) {
                prev = j
                break
            }
        }
        if (i - prev > 3 && length > 0) {
            sum += 5 + length * 2
            length = 1
        } else {
            length += i - prev
        }
    }
    if (length > 0)
        sum += 5 + length * 2
    println(sum)
}
