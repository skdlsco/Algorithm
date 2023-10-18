package `27951`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine()
    val hangerCount = Array<Int>(3) { 0 }
    val hangerArr = reader.readLine().split(" ").map { it.toInt() - 1 }
    var (U, D) = reader.readLine().split(" ").map { it.toInt() }
    hangerArr.forEach {
        hangerCount[it]++
    }
    if (hangerCount[0] + hangerCount[2] < U || hangerCount[1] + hangerCount[2] < D) {
        writer.write("NO\n")
    } else {
        writer.write("YES\n")
        for (hanger in hangerArr) {
            when (hanger) {
                0 -> {
                    writer.write("U")
                    U--
                    hangerCount[0]--
                }

                1 -> {
                    writer.write("D")
                    D--
                    hangerCount[1]--
                }

                2 -> {
                    if (hangerCount[0] < U || D == 0) {
                        U--
                        writer.write("U")
                    } else {
                        D--
                        writer.write("D")
                    }
                }
            }
        }
    }
    writer.flush()
}