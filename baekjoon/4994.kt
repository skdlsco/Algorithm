import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val n = reader.readLine().toInt()
        if (n == 0)
            break
        val dp = Array<Array<Int>>(101) { Array(n) { -1 } }
        // [][] = prev mod
        val path = Array<Array<Int>>(101) { Array(n) { 0 } }
        dp[0][0] = 0
        var find = 0
        for (i in 0 until 100) {
            for (j in 0 until n) {
                if (dp[i][j] == -1)
                    continue
                val num1 = (j * 10 + 1) % n
                val num0 = (j * 10) % n
                dp[i + 1][num1] = 1
                path[i + 1][num1] = j
                if (i != 0) {
                    dp[i + 1][num0] = 0
                    path[i + 1][num0] = j
                }
                if (num1 == 0)
                    find = i + 1
                if (i != 0 && num0 == 0)
                    find = i + 1
                if (find > 0)
                    break
            }
            if (find > 0)
                break
        }
        val ans = StringBuilder()
        var temp = 0
        for (i in find downTo 1) {
            ans.append("${dp[i][temp]}")
            temp = path[i][temp]
        }
        ans.reverse()
        writer.write("${ans}\n")
    }
    writer.flush()
}