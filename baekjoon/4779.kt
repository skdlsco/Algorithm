package `4779`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun solve(N: Int, sb: StringBuilder): String {
    if (N == 0) {
        return sb.toString()
    }
    val temp = sb.toString()
    repeat(sb.length) {
        sb.append(" ")
    }
    sb.append(temp)
    return solve(N - 1, sb)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val N = reader.readLine()?.toInt() ?: break
        val ans = solve(N, StringBuilder("-"))
        writer.write(ans)
        writer.newLine()
    }
    writer.flush()
}
    