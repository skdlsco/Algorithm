package `1182`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, S) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var cnt = 0
    (1 until 2.0.pow(N).toInt()).forEach {
        var bit = it
        val sum = arr.sumOf {
            val num = if (bit % 2 == 1)
                it
            else 0
            bit = bit.shr(1)
            num
        }
        if (S == sum)
            cnt++
    }
    println(cnt)
}