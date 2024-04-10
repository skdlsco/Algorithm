import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    // i 전기 용품이 몇개 들어와있는지
    val plug = Array<Int>(K + 1) { 0 }
    // [i, v] i전기 용품의 다음은 v번째 출현
    val pq = PriorityQueue<Pair<Int, Int>>() { e1, e2 ->
        e2.second - e1.second
    }
    // [i].first() -> i 전기용품의 다음 출현
    val arr = Array<Queue<Int>>(K + 1) { LinkedList() }
    val input = reader.readLine().split(" ").map { it.toInt() }

    input.forEachIndexed { index, i ->
        arr[i].add(index)
    }
    var kind = 0
    var idx = 0
    var cnt = 0
    for (cur in input) {
        idx++
        arr[cur].remove()
        // 이미 꽂혀 있는 전기용품은 패스
        val last = if (arr[cur].isEmpty()) Int.MAX_VALUE else arr[cur].peek()
        if (plug[cur] > 0) {
            pq.add(Pair(cur, last))
            continue
        }
        kind++
        plug[cur]++
        // 가득찬 경우
        while (pq.isNotEmpty() && pq.peek().second < idx && plug[pq.peek().first] > 1) {
            pq.remove()
        }
        while (pq.isNotEmpty() && kind > N) {
            val (del, _) = pq.remove()
            plug[del]--
            if (plug[del] == 0) {
                cnt++
                kind--
            }
        }
        pq.add(Pair(cur, last))
    }
    println(cnt)
}