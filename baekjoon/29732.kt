package `29732`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().toCharArray()
    var left = -1
    for (x in 0 until N) {
        val cur = arr[x]
        if (cur == '.') {
            if (x <= left)
                arr[x] = 'R'
            continue
        }
        left = x + K
        for (i in x - 1 downTo max(0, x - K)) {
            if (arr[i] == 'R')
                break
            arr[i] = 'R'
        }
    }
    val cnt = arr.count { it == 'R' }
    if (cnt <= M)
        writer.write("Yes")
    else
        writer.write("No")
    writer.flush()
}
    