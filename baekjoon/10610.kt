import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val str = reader.readLine().toCharArray().sortedDescending()

    if (str.last() != '0' || str.sumOf { it.digitToInt() } % 3 != 0)
        writer.write("-1")
    else
        writer.write(str.joinToString(""))
    writer.flush()
}
