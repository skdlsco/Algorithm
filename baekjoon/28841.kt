package `28841`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val S = reader.readLine()
    if (S.length == 1 || (S.length == 2 && S.sumOf { it.digitToInt() } == 1)) {
        writer.write("-1")
    } else {
        val oneFirst = S.indexOfFirst { it == '1' } + 1
        val oneLast = S.indexOfLast { it == '1' } + 1
        val zeroFirst = S.indexOfFirst { it == '0' } + 1
        val zeroLast = S.indexOfLast { it == '0' } + 1
        if (oneLast - oneFirst > zeroLast - zeroFirst) {
            writer.write("${oneFirst} ${oneLast - 1} ${oneFirst + 1} ${oneLast}")
        } else {
            writer.write("${zeroFirst} ${zeroLast - 1} ${zeroFirst + 1} ${zeroLast}")
        }
    }
    writer.newLine()
    writer.flush()
}
    