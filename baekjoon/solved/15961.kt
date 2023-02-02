package `15961`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toInt()
    val d = stringTokenizer.nextToken().toInt()
    val k = stringTokenizer.nextToken().toInt()
    val c = stringTokenizer.nextToken().toInt()
    val arr = Array<Int>(N * 2) { 0 }
    val eat = Array<Int>(d + 1) { 0 }
    var maxValue = 0

    repeat(N) {
        arr[it] = reader.readLine().toInt()
        arr[it + N] = arr[it]
    }
    var idx = 0
    var kinds = 1
    eat[c]++
    while (idx < N * 2) {
        if (idx >= k) {
            if (eat[arr[idx]] == 0)
                kinds++
            eat[arr[idx]]++
            eat[arr[idx - k]]--
            if (eat[arr[idx - k]] == 0)
                kinds--
        } else {
            if (eat[arr[idx]] == 0)
                kinds++
            eat[arr[idx]]++
        }
        if (kinds > maxValue)
            maxValue = kinds
        idx++
    }
    println(maxValue)
}