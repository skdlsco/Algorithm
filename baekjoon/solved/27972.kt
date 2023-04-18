package `27972`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val M = reader.readLine().toInt()
    val stringTokenizer = StringTokenizer(reader.readLine())
    // 0, 1: asc, 2: desc
    var state = 0
    var result = 1
    var N = 1
    var prev = stringTokenizer.nextToken().toInt()
    repeat(M - 1) {
        val cur = stringTokenizer.nextToken().toInt()
        if (cur > prev) {
            if (state == 2) {
                N = 2
            } else
                N++
            state = 1
        } else if (cur < prev) {
            if (state == 1) {
                N = 2
            } else
                N++
            state = 2
        }
        result = max(result, N)
        prev = cur
    }
    println(result)
}