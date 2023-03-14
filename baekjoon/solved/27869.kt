package `27869`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import java.util.TreeSet
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    val prefixSum = Array<Long>(N + 1) { 0 }
    var stringTokenizer = StringTokenizer(reader.readLine())
    for (i in 1..N) {
        val cur = stringTokenizer.nextToken().toLong()
        prefixSum[i] = prefixSum[i - 1] + cur
    }

    val treeSet = TreeSet<Int>()
    repeat(Q) {
        stringTokenizer = StringTokenizer(reader.readLine())
        val command = stringTokenizer.nextToken().toInt()
        if (command == 1) {
            treeSet.add(stringTokenizer.nextToken().toInt())
        } else {
            var s = stringTokenizer.nextToken().toInt()
            val e = stringTokenizer.nextToken().toInt()
            if (treeSet.isNotEmpty()) {
                val lower = treeSet.lower(e)
                if (lower != null)
                    s = max(s, lower + 1)
            }
            writer.write("${prefixSum[e] - prefixSum[s - 1]}\n")
        }
    }
    writer.flush()
}