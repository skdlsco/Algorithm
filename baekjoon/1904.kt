import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N + 1) { 0 }
    arr[0] = 1
    arr[1] = 1
    for (i in 2..N) {
        arr[i] = (arr[i - 1] + arr[i - 2]) % 15746
    }
    writer.write("${arr[N]}\n")
    writer.flush()
}