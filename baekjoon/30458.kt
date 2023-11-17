package `E`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val S = reader.readLine()
    val arr = Array<Int>(27) { 0 }

    for (i in 0 until N) {
        if (N % 2 == 1 && i == N / 2)
            continue
        arr[S[i].code - 'a'.code]++
    }
    val able = arr.all { it % 2 == 0 }
    if (able)
        writer.write("Yes")
    else
        writer.write("No")
    writer.flush()
}
    