package `27968`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val candy = Array<Long>(M + 1) { 0 }
    reader.readLine().split(" ").forEachIndexed { idx, c ->
        candy[idx + 1] = candy[idx] + c.toLong()
    }
    repeat(N) {
        val need = reader.readLine().toLong()
        if (need > candy.last()) {
            writer.write("Go away!\n")
            return@repeat
        }
        var left = 0
        var right = M
        while (left < right) {
            val mid = (left + right) / 2
            if (candy[mid] < need)
                left = mid + 1
            else
                right = mid
        }
        writer.write("${left}\n")
    }
    writer.flush()
}