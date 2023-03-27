package `27919`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val line = reader.readLine()
    var U = 0
    var D = 0
    line.forEach {
        if (it == 'U' || it == 'C')
            U++
        if (it == 'D' || it == 'P')
            D++
    }
    val minDP = D / 2 + if (D % 2 == 1) 1 else 0
    if (minDP < U)
        writer.write("U")
    if (D > 0)
        writer.write("DP")
    if (U == 0 && D == 0)
        writer.write("C")
    writer.flush()
}