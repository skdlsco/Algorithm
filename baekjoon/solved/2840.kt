package `2840`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val wheel = Array<Char>(N) { '?' }
    var cursor = 0
    var isFailed = false
    repeat(K) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val S = stringTokenizer.nextToken().toInt()
        val c = stringTokenizer.nextToken()[0]

        if (isFailed)
            return@repeat
        if (wheel[(cursor + S) % N] != '?' && wheel[(cursor + S) % N] != c)
            isFailed = true
        wheel[(cursor + S) % N] = c
        cursor = (cursor + S) % N
    }
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (i != j && wheel[i] != '?' && wheel[j] != '?' && wheel[i] == wheel[j])
                isFailed = true
        }
    }
    if (isFailed)
        writer.write("!")
    else {
        for (i in cursor downTo 0) {
            writer.write("${wheel[i]}")
        }
        for (i in N - 1 downTo cursor + 1) {
            writer.write("${wheel[i]}")
        }
    }
    writer.write("\n")
    writer.flush()
}