package `28449`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun lowerBound(array: List<Int>, target: Int): Int {
    var left = 0
    var right = array.size
    while (left < right) {
        val mid = (left + right) / 2
        if (array[mid] < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}

fun upperBound(array: List<Int>, target: Int): Int {
    var left = 0
    var right = array.size
    while (left < right) {
        val mid = (left + right) / 2
        if (array[mid] <= target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val hiTeam = reader.readLine().split(" ").map { it.toInt() }.sorted()
    val arcTeam = reader.readLine().split(" ").map { it.toInt() }.sorted()

    var win = 0L
    var lose = 0L
    var draw = 0L
    for (p in hiTeam) {
        val up = upperBound(arcTeam, p)
        val low = lowerBound(arcTeam, p)
        win += low.toLong()
        lose += M - up.toLong()
        draw += up.toLong() - low.toLong()
    }
    writer.write("${win} ${lose} ${draw}\n")
    writer.flush()
}
    