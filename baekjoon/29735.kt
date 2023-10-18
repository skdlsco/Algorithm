package `29735`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (startHM, endHM) = reader.readLine().split(" ").map { it.split(":") }
    val (N, T) = reader.readLine().split(" ").map { it.toInt() }
    val start = startHM[0].toInt() * 60 + startHM[1].toInt()
    val end = endHM[0].toInt() * 60 + endHM[1].toInt()
    val daily = (end - start - 1) / T
    val day = N / daily
    val last = N % daily
    val lastTime = start + (last + 1) * T
    writer.write("${day}\n")
    writer.write("${"%02d".format(lastTime / 60)}:${"%02d".format(lastTime % 60)}\n")
    writer.flush()
}
    