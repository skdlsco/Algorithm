package `30020`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (A, B) = reader.readLine().split(" ").map { it.toInt() }
    if (A > B && A - B <= B) {
        writer.write("YES\n")
        writer.write("${A - B}\n")
        repeat(A - B - 1) {
            writer.write("aba\n")
            A -= 2
            B--
        }
        repeat(B) {
            writer.write("ab")
        }
        writer.write("a\n")
    } else {
        writer.write("NO\n")
    }
    writer.flush()
}