package `25561`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val t = reader.readLine().toInt()

    repeat(t) {
        val n = reader.readLine().toInt()
        val arr = reader.readLine().split(" ").map { it.toInt() }
        var s = arr[0]
        (1 until n).forEach {
            val now = arr[it]
            if (arr[it - 1] < now || s < now * 2) {
                writer.write("NO\n")
                return@repeat
            }
            s -= now
        }
        if (arr.last() > 2 || (n > 2 && arr[n - 2] <= 1))
            writer.write("NO\n")
        else if (s == 2)
            writer.write("YES\n")
        else
            writer.write("NO\n")

    }
    writer.flush()
}

