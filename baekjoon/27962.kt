package `27962`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val line = reader.readLine()
    for (len in 1..N) {
        val a = line.substring(0 until len)
        val b = line.substring(N - len until N)
        var cnt = 0
        for (i in 0 until len) {
            if (a[i] != b[i])
                cnt++
            if (cnt > 1)
                break
        }
        if (cnt == 1) {
            println("YES")
            return
        }
    }
    println("NO")
}