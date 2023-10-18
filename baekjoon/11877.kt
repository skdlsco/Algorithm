import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, X) = reader.readLine().split(" ").map { it.toLong() }
    if (X > (N - 1) * (N - 2) / 2) {
        writer.write("-1\n")
    } else {
        var sum = 0L
        val print = BooleanArray(N.toInt() + 1) { false }
        writer.write("${N} ")
        for (i in 1 until N - 1) {
            if (sum + N - 1 - i > X)
                print[i.toInt()] = true
            else {
                sum += N - 1 - i
                writer.write("${i} ")
            }
        }
        if (N - 1 > 0)
            writer.write("${N - 1} ")
        for (i in N - 2 downTo 1) {
            if (print[i.toInt()])
                writer.write("${i} ")
        }
    }
    writer.flush()
}
