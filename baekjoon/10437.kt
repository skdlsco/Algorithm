package `10437`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val P = reader.readLine().toInt()
    repeat(P) {
        val (T, X, Y) = reader.readLine().split(" ").map { it.toInt() }
        if (Y > X) {
            // 2회만에 바로 가능하다.
            writer.write("$T 2 ${X} ${Y}\n")
        } else if (X > 2 && Y > 3) {
            writer.write("$T 6 1 2 3 ")
            writer.write("${5 + X - Y} ${X + 2} ${X + 3}\n")
        } else {
            writer.write("${T} NO PATH\n")
        }
    }
    writer.flush()
}
