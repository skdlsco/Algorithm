package `28062`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val candyArr = reader.readLine().split(" ").map { it.toInt() }
    var minValue = Int.MAX_VALUE
    var oddCnt = 0
    var sum = 0
    candyArr.forEach {
        if (it % 2 == 1) {
            oddCnt++
            minValue = min(minValue, it)
        }
        sum += it
    }
    if (oddCnt % 2 == 1)
        sum -= minValue
    writer.write("$sum")
    writer.flush()
}