package `29759`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val N = reader.readLine().toInt()
    val set = HashSet<Int>()
    val board = Array<Array<Int>>(N * N) { Array(N * N) { 0 } }
    for (y in 0 until N * N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N * N) {
            board[y][x] = row[x]
            if (row[x] != 0)
                set.add(row[x])
        }
    }
    val sieve = Array<Boolean>(1000001) { true }
    val list = ArrayList<Int>()
    for (i in 2 until 1000000) {
        if (sieve[i]) {
            if (i > 500000)
                list.add(i)
            for (j in i until 1000000 step i) {
                sieve[j] = false
            }
        }
    }
    var idx = 0
    for (y in 0 until N * N) {
        for (x in 0 until N * N) {
            if (board[y][x] == 0) {
                while (set.contains(list[idx]))
                    idx++
                set.add(list[idx])
                writer.write("${list[idx]} ")
            } else {
                writer.write("${board[y][x]} ")
            }
        }
        writer.newLine()
    }
    writer.flush()
}
    