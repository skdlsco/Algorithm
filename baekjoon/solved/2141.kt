package `2141`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array(N) {
        val strTokenizer = StringTokenizer(reader.readLine())
        Pair(strTokenizer.nextToken().toLong(), strTokenizer.nextToken().toLong())
    }
    arr.sortBy { it.first }
    val prefixSum = Array<Long>(N) { 0 }
    prefixSum[0] = arr[0].second
    for (i in 1 until N) {
        prefixSum[i] = prefixSum[i - 1] + arr[i].second
    }
    // 사람의 수만 가지고 생각하기
    // 왼쪽 사람과 오른쪽 사람 비교해서 더 많은 사람이 있는 쪽으로 움직인다.
    // i 가 0..N으로 간다고 했을 때
    // i+1 될 때 i일 때 왼쪽 사람수 만큼 -되고 오른쪽 사람수 만큼 +된다. 그러므로
    // 왼쪽에서부터 오른쪽으로 간다고 했을 때 오른쪽 사람이 더 많은 경우 오른쪽으로 가는 것이 이득
    // 오른쪽으로 가는 것이 더 이득이 아니게 될 때가 최소거리 위치. -> 같은 경우는 +- 0가 되어서 값이 변하지 않는다.
    for (i in 0 until N) {
        if (prefixSum[N - 1] - prefixSum[i] <= prefixSum[i]) {
            println(arr[i].first)
            break
        }
    }
}