import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val input = Array<Pair<Int, Int>>(N) { Pair(0, 0) }
    repeat(N) {
        val (A, B) = reader.readLine().split(" ").map { it.toInt() }
        input[it] = Pair(A, B)
    }
    input.sortBy { it.first }
    val arr = input.map { it.second }
    val dp = Array<Int>(N) { Int.MAX_VALUE }
    for (i in 0 until N) {
        val cur = arr[i]
        var left = 0
        var right = N - 1
        while (left < right) {
            val mid = (left + right) / 2
            if (dp[mid] < cur)
                left = mid + 1
            else
                right = mid
        }
        dp[left] = arr[i]
    }
    writer.write("${dp.count { it == Int.MAX_VALUE }}")
    writer.flush()
}
