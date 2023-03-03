package `15732`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

data class Info(val start: Int, val end: Int, val interval: Int)

fun isNeedMoreBox(boxNum: Int, acorn: Int, rule: List<Info>): Boolean {
    var cnt = 0
    rule.forEach {
        if (boxNum >= it.start) {
            // 0부터 새므로 +1
            cnt += (min(boxNum, it.end) - it.start) / it.interval + 1
        }
        if (acorn <= cnt)
            return false
    }
    return acorn > cnt
}

// 왜 이분 탐색을 생각했지?
// 매 상자마다 규칙을 대입해서 도토리 개수를 새면 시간 초과가 된다.
//  -> N(1,000,000) * K(10,000)
// 마지막 상자를 찾아야 한다.
//  -> 특정 상자 번호에 대해 상자가 부족한지 충분한지 K(10,000)번으로 알 수 있다.
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, K, D) = reader.readLine().split(" ").map { it.toInt() }
    val rule = ArrayList<Info>()

    repeat(K) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        rule.add(
            Info(
                stringTokenizer.nextToken().toInt(),
                stringTokenizer.nextToken().toInt(),
                stringTokenizer.nextToken().toInt()
            )
        )
    }

    var left = 0
    var right = N
    while (left < right) {
        val mid = (left + right) / 2

        if (isNeedMoreBox(mid, D, rule)) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    println(right)
}