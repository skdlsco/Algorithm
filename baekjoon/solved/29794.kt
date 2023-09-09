package `29794`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val originLv = Array<Int>(202) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEach { originLv[it]++ }
    val target = Array<Long>(201) { -1L }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { index, monster ->
        target[monster] = index + 1L
    }
    for (i in 1..200) {
        if (target[i] == -1L)
            target[i] = target[i - 1]
    }
    var defaultSum = 0L
    var level = originLv.copyOf()
    for (k in 0 until K) {
        for (i in 199 downTo 1) {
            level[i + 1] += level[i]
            defaultSum += (target[i] - 1) * level[i]
            level[i] = 0
        }
    }
    var best = Long.MAX_VALUE
    var bestIdx = 0
    for (t in 2..M) {
        level = originLv.copyOf()
        var sum = 0L
        for (k in 0 until K) {
            for (i in 199 downTo 1) {
                level[i + 1] += level[i]
                val usePortal = abs(target[i] - t)
                if (usePortal < (target[i] - 1))
                    sum += usePortal * level[i]
                else
                    sum += (target[i] - 1) * level[i]
                level[i] = 0
            }
        }
        if (best > sum) {
            bestIdx = t
            best = sum
        }
    }
    println("${1} ${bestIdx}")
    println(defaultSum - best)
    writer.flush()
}