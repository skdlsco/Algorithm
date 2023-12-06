import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<String>(N) { reader.readLine() }
    val result = Array<Char>(arr[0].length) { idx ->
        if (arr.all { it[idx] == arr[0][idx] })
            arr[0][idx]
        else
            '?'
    }
    writer.write(result.joinToString(""))
    writer.flush()
}