package `2212`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val K = reader.readLine().toInt()
    val sensors = reader.readLine().split(" ").map { it.toInt() }.sorted()
    val distance = Array<Int>(N - 1) { 0 }

    if (K >= N) {
        println(0)
        return
    }

    var sum = 0
    for (i in 0 until N - 1) {
        distance[i] = sensors[i + 1] - sensors[i]
        sum += distance[i]
    }
    // K개 만큼 놓을 수 있다.

    // 집중국 하나는 하나의 범위를 갖는다. i..j(i <= j) 까지 커버가 된다고 할 때 집중국의 길이는 j - i
    // 각 센서의 간격이 큰 순서대로 정렬
    // K - 1번 전체 합에서 해당간격만큼 뺀다. (해당 부분을 건너뛰도록 한다)
    distance.sortDescending()
    for (i in 0 until K - 1) {
        sum -= distance[i]
    }
    println(sum)
}