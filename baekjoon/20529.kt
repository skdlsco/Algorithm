package `20529`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun back(arr: List<String>, N: Int, cur: Int, depth: Int, select: Array<String>): Int {
    if (depth == 3) {
        var sum = 0
        for (i in 0 until 2) {
            for (j in i + 1 until 3) {
                for (k in 0 until 4)
                    if (select[i][k] != select[j][k])
                        sum++
            }
        }
        return sum
    }
    var ans = Int.MAX_VALUE
    for (i in cur until N) {
        select[depth] = arr[i]
        ans = minOf(ans, back(arr, N, i + 1, depth + 1, select))
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val arr = reader.readLine().split(" ")
        if (N >= 48)
            writer.write("0\n")
        else
            writer.write("${back(arr, N, 0, 0, Array<String>(3) { "" })}\n")
    }
    writer.flush()
}
    