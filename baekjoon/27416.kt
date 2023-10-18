package `27416`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Data(val id: Int, val a: Int, val b: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Data>(n) { Data(0, 0, 0) }
    val result = Array<Int>(n) { 0 }
    repeat(n) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        arr[it] = Data(it, a, b)
    }
    arr.sortWith() { o1, o2 ->
        if (o1.b == o2.b)
            o2.a - o1.a
        else
            o1.b - o2.b
    }
    for (i in 1..31) {
        var cnt = 0
        for (d in arr) {
            if (result[d.id] != 0)
                continue
            if (i in d.a..d.b) {
                result[d.id] = i
                cnt++
            }
            if (cnt >= k)
                break
        }
    }
    writer.write(result.joinToString("\n"))
    writer.flush()
}