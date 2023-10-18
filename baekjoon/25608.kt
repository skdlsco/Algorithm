package `25608`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

data class Data(val left: Int, val right: Int, val all: Int)

fun getPrefixMax(size: Int, arr: Array<Int>): Int {
    var prev = 0
    var ans = prev
    for (i in 0 until size) {
        prev = prev + arr[i]
        ans = max(ans, prev)
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { Array(M) { 0 } }
    repeat(N) {
        arr[it] = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    }
    val datas = arr.map {
        val right = getPrefixMax(M, it)
        val left = getPrefixMax(M, it.reversed().toTypedArray())
        val all = max(0, it.sumOf { it })
        Data(left, right, all)
    }
    var ans = 0
    for (l in 0 until N) {
        for (r in 0 until N) {
            if (l == r)
                continue
            var sum = datas[l].left + datas[r].right
            for (mid in 0 until N) {
                if (mid != l && mid != r)
                    sum += datas[mid].all
            }
            ans = max(sum, ans)
        }
    }
    for (i in 0 until N) {
        var sum = 0
        for (j in 0 until M) {
            if (sum < 0)
                sum = arr[i][j]
            else
                sum += arr[i][j]
            ans = max(ans, sum)
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    