package `15552`

import java.io.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    (1..n).forEach{
        val s = reader.readLine().split(" ").sumBy { it.toInt() }
        writer.write(s.toString())
        writer.newLine()
    }
    reader.close()
    writer.close()
}
