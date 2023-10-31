import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dp = Array<Array<Array<Int>>>(21) { Array(21) { Array(21) { 0 } } }

fun w(a: Int, b: Int, c: Int): Int {
    if (a <= 0 || b <= 0 || c <= 0)
        return 1
    if (a > 20 || b > 20 || c > 20)
        return w(20, 20, 20)
    if (dp[a][b][c] != 0)
        return dp[a][b][c]
    if (a < b && b < c) {
        dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
    } else {
        dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)
    }
    return dp[a][b][c]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }
        if (a == -1 && b == -1 && c == -1)
            break
        writer.write("w(${a}, ${b}, ${c}) = ${w(a, b, c)}\n")
    }
    writer.flush()
}