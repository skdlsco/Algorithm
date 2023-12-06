package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val func = arrayOf(
        arrayOf(false, false, true, false, false),
        arrayOf(false, false, true, false, false),
        arrayOf(true, true, true, true, true),
        arrayOf(false, true, true, true, false),
        arrayOf(false, true, false, true, false))

fun fill(arr: Array<Array<Char>>, x: Int, y: Int, len: Int) {
    if (len == 1) {
        arr[y][x] = '*'
        return
    }
    for (dy in 0 until 5) {
        for (dx in 0 until 5) {
            if (func[dy][dx]) {
                fill(arr, x + dx * len / 5, y + dy * len / 5, len / 5)
            }
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    var len = 1
    repeat(N) {
        len *= 5
    }
    val arr = Array<Array<Char>>(len) { Array(len) { ' ' } }
    fill(arr, 0, 0, len)
    writer.write(arr.joinToString("\n") { it.joinToString("") })
    writer.flush()
}
    