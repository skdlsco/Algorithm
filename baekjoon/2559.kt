import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    var sum = 0
    for (i in 0 until K) {
        sum += arr[i]
    }
    var ans = sum
    for (i in K until N) {
        sum += arr[i]
        sum -= arr[i - K]
        ans = maxOf(ans, sum)
    }
    writer.write("$ans\n")
    writer.flush()
}
