package `27943`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun question(i: Long): String {
    writer.write("? ${i}\n")
    writer.flush()
    return reader.readLine()
}

fun main() {
    val N = reader.readLine().toLong()
    val center = N / 2 + 1
    val plantName = question(center)
    var left = 0L
    var right = center
    while (left < right) {
        val mid = (left + right) / 2
        if (mid == 0L) {
            left = 1
            break
        }
        val isEP = question(mid) == plantName
        if (isEP) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    val start = left
    left = center
    right = N
    while (left < right) {
        val mid = (left + right) / 2

        val isEP = question(mid) == plantName
        if (isEP) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    val isEP = question(right) == plantName

    val end = if (isEP) right else right - 1
    writer.write("! ${start} ${end}\n")
    writer.flush()
}