package `28601`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.sqrt

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val S = reader.readLine().toDouble()
    val side = sqrt(S).toLong()
    if (side * side < S) {
        if ((side + 1) * side < S)
            writer.write("${side * 2 + 2}")
        else
            writer.write("${side * 2 + 1}\n")
    } else {
        writer.write("${side * 2}\n")
    }
    writer.flush()
}
    