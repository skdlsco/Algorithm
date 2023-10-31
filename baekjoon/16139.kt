import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val line = reader.readLine()
    val arr = Array<Array<Int>>(26) { Array(line.length + 1) { 0 } }
    for (i in 1..line.length) {
        val code = line[i - 1].code - 'a'.code
        for (j in 0 until 26) {
            arr[j][i] = arr[j][i - 1]
        }
        arr[code][i]++
    }
    val N = reader.readLine().toInt()
    repeat(N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val code = stringTokenizer.nextToken()[0].code - 'a'.code
        val l = stringTokenizer.nextToken().toInt()
        val r = stringTokenizer.nextToken().toInt()
        writer.write("${arr[code][r + 1] - arr[code][l]}\n")
    }
    writer.flush()
}
