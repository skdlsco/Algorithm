package `27977`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (L, N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N + 1) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { index, i -> arr[index] = i }
    arr[N] = L
    var left = 1
    var right = L
    while (left < right) {
        val mid = (left + right) / 2

        var pos = 0
        var energy = mid
        var chance = K
        for (A in arr) {
            if (A - pos > energy) {
                if (A - pos > mid)
                    chance = -1
                energy = mid
                chance--
            }
            energy -= A - pos
            pos = A
        }
        if (chance < 0)
            left = mid + 1
        else
            right = mid
    }
    writer.write("${left}\n")
    writer.flush()
}