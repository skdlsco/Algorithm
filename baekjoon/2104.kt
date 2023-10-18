package `2104`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val stringTokenizer = StringTokenizer(reader.readLine())
    // 범위 중 작은 값을 곱하므로 stack을 오름차순으로 만든다.
    val stack = Stack<Pair<Long, Long>>()
    var result = 0L
    repeat(N) {
        val now = stringTokenizer.nextToken().toLong()
        var sum = 0L
        // 오름차순으로 하기 위해서
        // 자신보다 큰 경우 pop
        // pop 할 때 부분 배열의 합을 저장한다.
        // -> pop할 때마다 top이 부분배열의 최솟값
        // now를 stack에 넣을 때 부분배열의 합을 같이 저장한다.(now보다 높이가 큰 값들의 합)
        while (stack.isNotEmpty() && stack.peek().first >= now) {
            val top = stack.pop()
            sum += top.second
            result = max(result, sum * top.first)
        }
        sum += now
        stack.add(Pair(now, sum))
    }
    var sum = 0L
    while (stack.isNotEmpty()) {
        val top = stack.pop()
        sum += top.second
        result = max(result, sum * top.first)
    }
    println(result)
}