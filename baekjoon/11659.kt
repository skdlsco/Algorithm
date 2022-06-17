import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val array = reader.readLine().split(" ").map { it.toInt() }
    val sumArray = Array<Int>(N) { 0 }
    array.forEachIndexed { i, n ->
        if (i == 0)
            sumArray[i] = n
        else
            sumArray[i] = sumArray[i - 1] + n
    }
    repeat(M) {
        val (start, end) = reader.readLine().split(" ").map { it.toInt() - 1 }
        writer.write("${array[start] + sumArray[end] - sumArray[start]}")
        writer.newLine()
    }
    writer.flush()
}