import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun getSquares(): List<Long> {
    val sieve = Array<Boolean>(45001) { true }
    val squares = ArrayList<Long>()
    for (i in 2..45000) {
        if (sieve[i]) {
            squares.add(i * i.toLong())

            for (j in i + i..45000 step i) {
                sieve[j] = false
            }
        }
    }
    return squares
}

fun getCnt(squares: List<Long>, cur: Int, value: Long, target: Long): Long {
    if (cur >= squares.size)
        return 0L
    var sum = 0L
    if (value * squares[cur] > target)
        return 0L
    sum += target / (value * squares[cur])
    sum += getCnt(squares, cur + 1, value, target)
    sum -= getCnt(squares, cur + 1, value * squares[cur], target)
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val K = reader.readLine().toLong()
    val squares = getSquares()
    var lo = 0L
    var hi = 2_000_000_000L
    while (lo < hi) {
        val mid = (lo + hi) / 2
        val cnt = getCnt(squares, 0, 1, mid)
        if (mid - cnt < K)
            lo = mid + 1
        else
            hi = mid
    }
    writer.write("${lo}")
    writer.flush()
}