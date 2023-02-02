package `11660`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val input = List<List<Int>>(N) { reader.readLine().split(" ").map { it.toInt() } }
    val sumArr = Array<Array<Int>>(N) { Array(N) { 0 } }
    input.forEachIndexed { y, list ->
        list.forEachIndexed { x, i ->
            sumArr[y][x] = i
            if (y > 0)
                sumArr[y][x] += sumArr[y - 1][x]
            if (x > 0)
                sumArr[y][x] += sumArr[y][x - 1]
            if (x > 0 && y > 0)
                sumArr[y][x] -= sumArr[y - 1][x - 1]
        }
    }
    repeat(M) {
        val (y1, x1, y2, x2) = reader.readLine().split(" ").map { it.toInt() - 1 }
        var sum = sumArr[y2][x2]
        if (x1 > 0)
            sum -= sumArr[y2][x1 - 1]
        if (y1 > 0)
            sum -= sumArr[y1 - 1][x2]
        if (x1 > 0 && y1 > 0)
            sum += sumArr[y1 - 1][x1 - 1]
        writer.write("${sum}\n")
    }
    writer.flush()
}