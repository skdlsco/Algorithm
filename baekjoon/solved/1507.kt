package `1507`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 플로이드-와샬 ->  s-m-e < s-e 인경우 업데이트가 된다.
// s-m-e와 s-e가 같은 경우가 있으면 s-e가 없어도 같은 비용으로 연결이 될 수 있음을 의미
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            arr[y][x] = row[x]
        }
    }
    // true로 초기화
    val check = Array<Array<Boolean>>(N) { i -> Array(N) { j -> i != j } }
    var isImpossible = false
    for (mid in 0 until N) {
        for (start in 0 until N) {
            for (end in 0 until N) {
                if (start != mid && end != mid && arr[start][end] == arr[start][mid] + arr[mid][end])
                    check[start][end] = false
                // 더 빠른 길이 존재하는 경우 -> 입력이 잘못됨
                if (arr[start][end] > arr[start][mid] + arr[mid][end])
                    isImpossible = true
            }
        }
    }
    var sum = 0
    for (y in 0 until N) {
        for (x in 0 until N) {
            if (check[y][x])
                sum += arr[y][x]
        }
    }
    writer.write(if (isImpossible) "-1" else "${sum / 2}")
    writer.flush()
}