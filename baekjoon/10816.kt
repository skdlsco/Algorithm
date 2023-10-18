package `10816`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun lowerBound(deck: List<Int>, target: Int): Int {
    var left = 0
    var right = deck.size
    while (left < right) {
        val mid = (left + right) / 2
        if (deck[mid] < target)
            left = mid + 1
        else right = mid
    }
    return left
}

fun upperBound(deck: List<Int>, target: Int): Int {
    var left = 0
    var right = deck.size
    while (left < right) {
        val mid = (left + right) / 2
        if (deck[mid] <= target)
            left = mid + 1
        else right = mid
    }
    return left
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val deck = reader.readLine().split(" ").map { it.toInt() }.sorted()
    val M = reader.readLine().toInt()
    val targets = reader.readLine().split(" ").map { it.toInt() }

    for (target in targets) {
        val lower = lowerBound(deck, target)
        val upper = upperBound(deck, target)
        writer.write("${upper - lower} ")
    }
    writer.flush()
}