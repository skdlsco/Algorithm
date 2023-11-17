import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.Stack
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.sinh


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val F = Array<Int>(1000001) { 0 }
    for (a in arr) {
        F[a]++
    }
    val result = Array<Int>(N) { -1 }
    val st = Stack<Pair<Int, Int>>()
    for ((i, a) in arr.withIndex()) {
        while (st.isNotEmpty() && st.peek().second < F[a]) {
            val (idx, _) = st.pop()
            result[idx] = a
        }
        st.add(Pair(i, F[a]))
    }
    while (st.isNotEmpty()) {
        val (idx, _) = st.pop()
        result[idx] = -1
    }
    writer.write(result.joinToString(" "))
    writer.flush()
}
