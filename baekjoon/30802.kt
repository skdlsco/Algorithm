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
    val shirts = reader.readLine().split(" ").map { it.toInt() }
    val (T, P) = reader.readLine().split(" ").map { it.toInt() }
    val shirtsOrder = shirts.sumOf {
        (it / T) + if (it % T == 0) 0 else 1
    }
    val penOrder = N / P
    val singlePen = N % P
    writer.write("${shirtsOrder}\n")
    writer.write("${penOrder} ${singlePen}\n")
    writer.flush()
}