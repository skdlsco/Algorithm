package `20056`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

data class Data(var m: Int, var s: Int, var d: Int)

val dx = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
val dy = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    var map = Array<Array<ArrayList<Data>>>(N) { Array(N) { ArrayList() } }
    repeat(M) {
        val (r, c, m, s, d) = reader.readLine().split(" ").map { it.toInt() }
        map[r - 1][c - 1].add(Data(m, s, d))
    }
    repeat(K) {
        // move
        val nextMap = Array<Array<ArrayList<Data>>>(N) { Array(N) { ArrayList() } }
        for (y in 0 until N) {
            for (x in 0 until N) {
                val fireballCollection = map[y][x]
                for (i in fireballCollection.indices) {
                    val curFireball = fireballCollection[i]
                    var ny = y + dy[curFireball.d] * curFireball.s
                    var nx = x + dx[curFireball.d] * curFireball.s
                    ny %= N
                    nx %= N
                    if (ny < 0)
                        ny += N
                    if (nx < 0)
                        nx += N
                    nextMap[ny][nx].add(curFireball)
                }
            }
        }

        map = nextMap
        // split
        for (y in 0 until N) {
            for (x in 0 until N) {
                val fireballCollection = map[y][x]
                if (fireballCollection.isEmpty())
                    continue
                if (fireballCollection.size <= 1) {
                    if (fireballCollection[0].m == 0)
                        fireballCollection.clear()
                    continue
                }
                val isAllOdd = fireballCollection.all { it.d % 2 == 1 }
                val isAllEven = fireballCollection.all { it.d % 2 == 0 }
                val nd = if (isAllOdd || isAllEven) arrayOf(0, 2, 4, 6) else arrayOf(1, 3, 5, 7)
                val nm = fireballCollection.sumOf { it.m } / 5
                val ns = fireballCollection.sumOf { it.s } / fireballCollection.size

                fireballCollection.clear()
                if (nm == 0)
                    continue
                fireballCollection.addAll(
                        nd.map { Data(nm, ns, it) }
                )
            }
        }
    }
    var sum = 0L
    for (y in 0 until N) {
        for (x in 0 until N) {
            sum += map[y][x].sumOf { it.m }
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
