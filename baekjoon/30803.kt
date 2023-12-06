import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Arrays
import java.util.StringTokenizer
import kotlin.math.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }.toTypedArray()
    val isOpen = Array<Boolean>(N) { true }
    var cur = arr.sumOf { it }
    writer.write("${cur}\n")
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val target = input.nextToken().toInt()- 1
            val v = input.nextToken().toLong()
            if (isOpen[target])
                cur += v - arr[target]
            arr[target] = v
        } else {
            val target = input.nextToken().toInt() - 1
            if (isOpen[target])
                cur -= arr[target]
            else
                cur += arr[target]
            isOpen[target] = !isOpen[target]
        }
        writer.write("${cur}\n")
    }
    writer.flush()
}