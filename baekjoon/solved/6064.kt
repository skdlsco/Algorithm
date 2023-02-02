package `6064`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val T = reader.readLine().toInt()
    repeat(T) {
        val (M, N, x, y) = reader.readLine().split(" ").map { it.toInt() }
        val (bigPair, smallPair) = if (M > N) {
            Pair(Pair(M, x - 1), Pair(N, y - 1))
        } else {
            Pair(Pair(N, y - 1), Pair(M, x - 1))
        }
        var cnt = smallPair.second
        val max = M * N / gcd(M, N)
        while (cnt % bigPair.first != bigPair.second && cnt <= max) {
            cnt += smallPair.first
        }
        if (cnt > max) {
            writer.write("-1")
            writer.newLine()
        } else {
            writer.write((cnt + 1).toString())
            writer.newLine()
        }
    }
    writer.flush()
}