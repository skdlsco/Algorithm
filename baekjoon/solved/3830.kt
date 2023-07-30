package `3830`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// sample[i] = Pair(a, b) i가 a보다 b만큼 무겁다
val samples = Array<Pair<Int, Int>>(101010) { Pair(it, 0) }

fun find(cur: Int): Int {
    if (samples[cur].first == cur)
        return cur
    val next = samples[cur].first
    val root = find(samples[cur].first)
    samples[cur] = Pair(root, samples[next].second + samples[cur].second)
    return root
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    // b - bRoot = x
    // a - aRoot = y
    // b - a = z
    // bRoot - aRoot = z + y - x
    while (true) {
        val (N, M) = reader.readLine().split(" ").map { it.toInt() }
        if (N == 0 && M == 0)
            break
        for (i in 1..N) {
            samples[i] = Pair(i, 0)
        }
        repeat(M) {
            val input = reader.readLine().split(" ")
            val command = input[0]
            val a = input[1].toInt()
            val b = input[2].toInt()
            val aRoot = find(a)
            val bRoot = find(b)
            if (command == "!") {
                val w = input[3].toInt()
                val calcW = samples[a].second + w - samples[b].second

                samples[bRoot] = Pair(aRoot, calcW)
            } else {
                if (aRoot != bRoot)
                    writer.write("UNKNOWN\n")
                else {
                    val calcW = samples[b].second - samples[a].second
                    writer.write("${calcW}\n")
                }
            }
            writer.flush()
        }
    }
    writer.flush()
}