package `E`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()

    var sum = 0
    var cnt = Array<Int>(4) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        cnt[it]++
    }
    val arr = ArrayList<Pair<Int, Pair<Int, Int>>>()
    for (i in 0..3) {
        for (j in i..3) {
            arr.add(Pair(i xor j, Pair(i, j)))
        }
    }
    arr.sortByDescending{ it.first }
    for (data in arr) {
        val (v, p) = data
        val temp = minOf(cnt[p.first], cnt[p.second])
        sum += temp * v
        cnt[p.first] -= temp
        cnt[p.second] -= temp
    }
    writer.write("${sum}\n")
    writer.flush()
}
    