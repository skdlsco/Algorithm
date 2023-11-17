package `C`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var first = reader.readLine().toInt()
    val result = Array<Char>(100) { '2' }
    val ans = Array<Char>(100) { '2' }
    for (i in 0 until 100) {
        result[i] = '5'
        writer.write("? ")
        writer.write(result.joinToString(""))
        writer.newLine()
        writer.flush()
        val cur = reader.readLine().toInt()
        // 0 -> + 1
        // 2 -> 0
        // 5 -> - 1
        if (cur == first) {
            ans[i] = '2'
        } else if (cur > first) {
            ans[i] = '0'
        } else {
            ans[i] = '5'
        }
        result[i] = '2'
    }
    writer.write("! ")
    writer.write(ans.joinToString(""))
    writer.newLine()
    writer.flush()
}
    