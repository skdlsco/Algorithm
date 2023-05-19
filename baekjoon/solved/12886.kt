package `12886`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stones = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    // 큰돌에서 작은 돌만큼 작은 돌을 옮기므로 합에는 영향이 없으므로 최대 값은 sum
    val maxValue = stones.sumOf { it }
    val visited = Array<Array<Boolean>>(maxValue + 1) { Array(maxValue + 1) { false } }
    if (maxValue % 3 != 0) {
        println("0")
        return
    }
    val queue: Queue<Array<Int>> = LinkedList()
    val temp = Array<Int>(2) { 0 }
    // 세 돌의 합은 변하지 않으므로 min, max를 키로 체크 가능
    visited[stones.minOf { it }][stones.maxOf { it }] = true
    queue.add(stones)
    while (queue.isNotEmpty()) {
        val stoneGroup = queue.remove()
        if (stoneGroup[0] == stoneGroup[1] && stoneGroup[1] == stoneGroup[2]) {
            println("1")
            return
        }
        for (other in 0 until 3) {
            var idx = 0
            for (i in 0 until 3) {
                if (other != i) {
                    temp[idx] = stoneGroup[i]
                    idx++
                }
            }
            val min = temp.minOf { it }
            val max = temp.maxOf { it }
            if (min == max)
                continue
            val next = arrayOf(min + min, max - min, stoneGroup[other])
            if (!visited[next.minOf { it }][next.maxOf { it }]) {
                visited[next.minOf { it }][next.maxOf { it }] = true
                queue.add(next)
            }
        }
    }
    println("0")
}