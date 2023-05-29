package `28072`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap
import kotlin.math.max

// 개수를 어떻게 셀 수 있는가? -> 누적합 + 나머지 0
// 원형인 경우 어떻게 구분할 수 있는가? -> 두번 이어붙인 후 슬라이딩윈도우
// 최빈값 찾기 -> 밀려있기 때문에 앞의 숫자 만큼 뒤의 값이 더해져있다. -> 같은 경우 0이 될 수 있다
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N + N) { 0 }
    var result = 0
    val input = reader.readLine().split(" ").map { it.toInt() }
    for (i in 0 until N) {
        arr[i] = input[i]
        arr[i + N] = input[i]
    }
    val prefixSumArr = Array<Int>(N + N + 1) { 0 }
    for (i in 1 until N + N) {
        prefixSumArr[i] = (prefixSumArr[i - 1] + arr[i - 1]) % K
    }
    var left = 1
    var right = 1
    val cntArr = Array<Int>(100001) { 0 }
    while (right <= N) {
        cntArr[prefixSumArr[right]]++
        result = max(cntArr[prefixSumArr[right]], result)
        right++
    }
    for (i in 0 until N - 1) {
        if (prefixSumArr[left] == 0)
            cntArr[prefixSumArr[left]]--
        if (prefixSumArr[right] == 0)
            cntArr[prefixSumArr[right]]++
        result = max(cntArr[prefixSumArr[right]], result)
        left++
        right++
    }
    writer.write("${result}")
    writer.flush()
}
