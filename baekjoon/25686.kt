import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.sinh

fun dfs(arr: Array<Int>, start: Int, end: Int, num: Int, step: Int) {
    if (start == end) {
        arr[start] = num
    } else {
        val mid = (start + end) / 2
        dfs(arr, start, mid, num, step * 2)
        dfs(arr, mid + 1, end, num + step, step * 2)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N + 1) { 0 }
    dfs(arr, 1, N, 1, 1)
    for (i in 1..N) {
        writer.write("${arr[i]} ")
    }
    writer.flush()
}
