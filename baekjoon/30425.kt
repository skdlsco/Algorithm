import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.sinh

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val sing = reader.readLine()
    val target = StringBuilder()
    var cnt = 0
    for (i in 0 until N) {
        var canSing = true
        for (j in 0 until N) {
            if (target.length <= i + j) {
                target.append(sing[j])
            } else if (sing[j] != target[i + j]) {
                canSing = false
                break
            }
        }
        if (canSing)
            cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}
