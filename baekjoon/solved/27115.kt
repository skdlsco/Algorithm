package `27115`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

data class Station(val y: Int, val x: Int, val p: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val queue = LinkedList<Station>()
    val map = Array<Array<Int>>(N) { Array(M) { -1 } }
    val arr = ArrayList<Station>(K)
    val dy = arrayOf(1, 0, -1, 0)
    val dx = arrayOf(0, 1, 0, -1)
    repeat(K) {
        val (y, x, p) = reader.readLine().split(" ").map { it.toInt() }
        arr.add(Station(y - 1, x - 1, p))
    }
    arr.sortBy { -it.p }
    var arrIdx = 0
    queue.add(arr[arrIdx])
    arrIdx = 1
    while(arrIdx < K && arr[arrIdx - 1].p == arr[arrIdx].p) {
        queue.add(arr[arrIdx])
        map[arr[arrIdx].y][arr[arrIdx].x] = arr[arrIdx].p
        arrIdx++
    }
    while (queue.isNotEmpty()) {
        val station = queue.pop()
        if (map[station.y][station.x] > station.p)
            continue
        map[station.y][station.x] = station.p
        while (arrIdx < K && station.p <= arr[arrIdx].p) {
            val now = arr[arrIdx]
            if (map[now.y][now.x] < now.p) {
                map[now.y][now.x] = now.p
                queue.add(arr[arrIdx])
            }
            arrIdx++
        }
        repeat(4) {
            val nextY = station.y + dy[it]
            val nextX = station.x + dx[it]
            val nextP = station.p - 1
            if (!(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) && map[nextY][nextX] < nextP){
                map[nextY][nextX] = nextP
                if (nextP != 0)
                    queue.add(Station(nextY, nextX, nextP))
            }
        }
    }
    println(map.sumOf { line -> line.count { it != -1 } })
}