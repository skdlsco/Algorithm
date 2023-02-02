package `15652`
import java.io.BufferedReader
import java.io.InputStreamReader

fun back2(N: Int, M: Int, arr: Array<Int>, n: Int, m: Int) {
    if (m == M) {
        println(arr.joinToString(" ") { "$it" })
        return
    }
    (n..N).forEach {
        arr[m] = it
        back2(N, M, arr, it, m + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    back2(N, M, Array(M) { 0 }, 1, 0)
}