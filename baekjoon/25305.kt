package `25305`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, k) = reader.readLine().split(" ").map { it.toInt() }
    val stringTokenizer = StringTokenizer(reader.readLine())
    val arr = Array<Int>(N) { stringTokenizer.nextToken().toInt() }
    arr.sort()
    println(arr[N - k])
}