package `16678`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()

    val arr = Array<Long>(N) { reader.readLine().toLong() }
    arr.sort()

    var need = 1L
    var sum = 0L
    for (cur in arr) {
        if (cur > need) {
            sum += cur - need
            need++
        } else if (cur == need)
            need++
    }
    writer.write("${sum}\n")
    writer.flush()
}