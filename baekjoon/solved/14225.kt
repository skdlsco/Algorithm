package `14225`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val S = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val visited = Array<Boolean>(2000001) { false }

    for (i in 0 until (1 shl N)) {
        var sum = 0
        for (j in 0 until N) {
            if (i and (1 shl j) > 0)
                sum += S[j]
        }
        visited[sum] = true
    }
    for (i in visited.indices) {
        if (!visited[i]) {
            writer.write("${i}\n")
            break
        }
    }
    writer.flush()
}