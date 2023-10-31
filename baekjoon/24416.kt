import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val f = Array<Int>(n + 1) { 1 }
    for (i in 3..n) {
        f[i] = f[i - 1] + f[i - 2]
    }
    writer.write("${f[n]} ${n - 2}\n")
    writer.flush()
}