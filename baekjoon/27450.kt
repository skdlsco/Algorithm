package `27450`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val nowArr = reader.readLine().split(" ").map { it.toInt() }
    val goalArr = reader.readLine().split(" ").map { it.toInt() }
    // <위치, 횟수>
    val queue = LinkedList<Pair<Int, Long>>()
    // 지금까지 외친 대사 수
    var result = 0L
    // 현재 부하에게 닿는 대사 수
    var cnt = 0L
    // 강화될 수치
    var power = 0L
    for (i in 0 until N) {
        // 현재 부하에게 강화되는 수치 = 이전 부하에게 강화된 수치 - 닿는 대사 수
        power -= cnt

        // 대사를 외치기 전에 판정하자
        // 왼쪽부터 목표치에 모자란 부하가 있으면 대사를 외친다!
        if (nowArr[i] + power < goalArr[i]) {
            val diff = goalArr[i] - (nowArr[i] + power)
            var append = diff / K
            // 올림
            if (diff % K > 0)
                append++
            cnt += append
            // power에 대사를 외친 수 * K를 더한다.
            power += append * K
            result += append
            queue.add(Pair(i, append))
        }
        // queue에 대사를 언제 쳤는지 체크.
        // 거리를 넘어간 대사가 있으면 제거한다.
        if (queue.isNotEmpty() && i - queue.peek().first >= K) {
            cnt -= queue.pop().second
        }
    }
    println(result)
}