package `24050`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun getTwoCount(num: Int): Int {
    var result = 0
    var temp = num
    while (temp > 0 && temp % 2 == 0) {
        temp /= 2
        result++
    }
    return result
}

// 각 칸이 N, M번째 칸까지 xor 연산을 몇번하는가? -> 파스칼의 삼각형이 나온다.
// N * M을 파스칼의 삼각형으로 그리면 길이 보인다.
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    var result = 0
    // (가로 + 세로 - 2) C (가로 - 1)
    // N 개
    val left = reader.readLine().split(" ").map { it.toInt() }
    // M 개
    val top = reader.readLine().split(" ").map { it.toInt() }
    // combination 시작 분모
    val startDenominator = N + M - 2
    // combination 시작 분자
    val startNumerator = M - 1
    var twoCount = 0
    for (i in 0..startNumerator) {
        twoCount += getTwoCount(startDenominator - i)
        twoCount -= getTwoCount(i)
    }
    val startTwoCount = twoCount

    for (i in 0 until N) {
        if (twoCount == 0)
            result = result xor left[i]
        twoCount -= getTwoCount(startDenominator - i)
        twoCount += getTwoCount(startDenominator - i - startNumerator)
    }
    twoCount = startTwoCount
    for (i in 0 until M) {
        if (twoCount == 0)
            result = result xor top[i]
        twoCount -= getTwoCount(startDenominator - i)
        twoCount += getTwoCount(startNumerator - i)
    }
    writer.write("${result}\n")
    writer.flush()
}