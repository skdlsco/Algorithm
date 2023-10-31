import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.sinh

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }.sortedDescending()
    if (N < 4) {
        writer.write("0\n")
    } else {
        val sticks = ArrayList<Long>()
        var sum = 0L
        var i = 0
        while (i < N - 1) {
            val a = arr[i]
            val b = arr[i + 1]
            i++

            if (a - 1 != b && a != b)
                continue
            sticks.add(b)
            i++
        }
        for (j in 0 until sticks.size - 1 step 2) {
            sum += sticks[j] * sticks[j + 1]
        }
        writer.write("${sum}\n")
    }
    writer.flush()
}
