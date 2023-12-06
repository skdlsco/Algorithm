import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.log2

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val input = reader.readLine().split(" ").map { it.toLong() }.sorted()
    var ans = arrayOf(1_000_000_000L, 1_000_000_000L, 1_000_000_000L)
    for (i in 0 until N) {
        var r = 0
        var l = N - 1
        while (r < l) {
            if (r == i)
                r++
            if (l == i)
                l--
            if (r >= l)
                break
            val cur = arrayOf(input[r], input[l], input[i])
            if (abs(ans.sum()) > abs(cur.sum()))
                ans = cur
            if (cur.sum() > 0)
                l--
            else
                r++
        }
    }
    writer.write(ans.sorted().joinToString(" "))
    writer.flush()
}