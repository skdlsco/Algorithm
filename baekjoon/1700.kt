package `1700`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    // [i]플러그에 꽂힌 전기용품
    val plug = Array<Int>(N) { 0 }

    // [i].first() -> i 전기용품의 다음 출현
    val arr = Array<Queue<Int>>(K + 1) { LinkedList() }
    val input = reader.readLine().split(" ").map { it.toInt() }

    input.forEachIndexed { index, i ->
        arr[i].add(index)
    }
    var cnt = 0
    input.forEach {
        // 이미 꽂혀 있는 전기용품은 패스
        for (i in 0 until N) {
            if (plug[i] == it) {
                arr[it].remove()
                return@forEach
            }
        }
        // 비어있는 칸이 있는경우
        for (i in 0 until N) {
            if (plug[i] == 0) {
                plug[i] = it
                arr[it].remove()
                return@forEach
            }
        }
        // 가득찬 경우
        cnt++
        var maxIdx = 0
        for (i in 0 until N) {
            if (arr[plug[i]].isEmpty())
                maxIdx = i
            else if (arr[plug[maxIdx]].isNotEmpty() && arr[plug[maxIdx]].peek() < arr[plug[i]].peek()) {
                maxIdx = i
            }
        }
        plug[maxIdx] = it
        arr[it].remove()
    }
    println(cnt)
}