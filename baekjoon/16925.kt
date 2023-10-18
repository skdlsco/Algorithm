package `16925`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun valid(N: Int, str: String, arr: Array<String>): Boolean {
    val check = Array<Array<Boolean>>(N) { Array(2) { false } }
    var cnt = 0
    for (s in arr) {
        val n = s.length
        if (!check[n][0]) {
            if (str.startsWith(s)) {
                check[n][0] = true
                cnt++
                continue
            }
        }
        if (!check[n][1]) {
            if (str.endsWith(s)) {
                check[n][1] = true
                cnt++
                continue
            }
        }
    }
    return cnt == (N - 1) * 2
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<String>((N - 1) * 2) { reader.readLine() }
    val ps = ArrayList<String>()
    for (s in arr) {
        if (s.length == N - 1) {
            ps.add(s)
        }
    }
    val ab = ps[0].first() + ps[1]
    val ba = ps[1].first() + ps[0]
    val str = if (!valid(N, ab, arr)) ba else ab
    val check = Array<Array<Boolean>>(N) { Array(2) { false } }
    writer.write(str)
    writer.newLine()
    for (s in arr) {
        val n = s.length
        if (!check[n][0]) {
            if (str.startsWith(s)) {
                check[n][0] = true
                writer.write("P")
                continue
            }
        }
        if (!check[n][1]) {
            if (str.endsWith(s)) {
                check[n][1] = true
                writer.write("S")
                continue
            }
        }
    }
    writer.flush()
}
    