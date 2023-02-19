package `1493`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Long.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (l, w, h) = reader.readLine().split(" ").map { it.toLong() }
    val n = reader.readLine().toInt()
    val cube = Array<Long>(20) { 0 }
    repeat(n) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        cube[a] += b.toLong()
    }
    // 각 단계로 채운 직사각형수
    var volumeCnt = 0L
    // 사용한 큐브 수
    var cubeCnt = 0L
    // 2의 i 승 큐브로 현재 공간을 채워보자
    for (i in 19 downTo 0) {
        // 이전 단계에서 채운 직사각형 갯수가 크기가 한단계 줄어서 2^3배로 증가
        volumeCnt = volumeCnt shl 3
        // 2^i 큐브 들어가 들어 갈 수 있는 갯수
        // -> (l shr i) * (w shr i) * (h shr i)
        // cube[i] -> 소지한 2^i 큐브 갯수
        // 최대한 넣어야 하므로 min(있는거 전부, 들어 갈 수 있는 최대치 - 이미 채운 갯수)
        val append = min(cube[i], (l shr i) * (w shr i) * (h shr i) - volumeCnt)
        volumeCnt += append
        cubeCnt += append
    }
    // 직사각형의 수가 l w h합이 안되는 경우 -> 큐브가 부족했다.
    if (volumeCnt != l * w * h) {
        println(-1)
    } else
        println(cubeCnt)
}