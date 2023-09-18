package `29713`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = HashMap<Char, Int>()
    reader.readLine().forEach {
        map[it] = (map[it] ?: 0) + 1
    }
    val ans = minOf(
        map['B'] ?: 0,
        (map['R'] ?: 0) / 2,
        map['O'] ?: 0,
        map['N'] ?: 0,
        map['Z'] ?: 0,
        (map['E'] ?: 0) / 2,
        map['S'] ?: 0,
        map['I'] ?: 0,
        map['L'] ?: 0,
        map['V'] ?: 0
    )
    writer.write("${ans}\n")
    writer.flush()
}
    