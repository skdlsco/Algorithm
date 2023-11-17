import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val book = Array<ArrayList<Int>>(11) { ArrayList() }
    repeat(N) {
        val (C, G) = reader.readLine().split(" ").map { it.toInt() }
        book[G].add(C)
    }
    book.forEach { it.sortDescending() }
    val bookPrefix = Array<Array<Long>>(11) { Array(book[it].size + 1) { 0L } }
    for (genre in 1..10) {
        var t = 0L
        for (i in 1..book[genre].size) {
            bookPrefix[genre][i] = bookPrefix[genre][i - 1] + book[genre][i - 1] + t
            t += 2
        }
    }
    val dp = Array<Long>(K + 1) { 0L }
    for (genre in 1..10) {
        val temp = dp.copyOf()
        for (k in 1..minOf(K, bookPrefix[genre].lastIndex)) {
            for (i in K downTo k) {
                dp[i] = maxOf(dp[i], temp[i - k] + bookPrefix[genre][k])
            }
        }
    }
    writer.write("${dp[K]}\n")
    writer.flush()
}