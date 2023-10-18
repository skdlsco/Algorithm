package `9506`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val N = reader.readLine().toInt()
        if (N == -1)
            break
        var sum = 1
        val stringBuilder = StringBuilder()
        stringBuilder.append("${N} = 1 ")
        for (i in 2 until N) {
            if (N % i == 0) {
                sum += i
                stringBuilder.append("+ ${i} ")
            }
        }
        if (sum == N)
            writer.write(stringBuilder.toString())
        else
            writer.write("${N} is NOT perfect.")
        writer.newLine()
    }
    writer.flush()
}