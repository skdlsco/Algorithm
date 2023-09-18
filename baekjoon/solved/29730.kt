package `29730`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val boj = ArrayList<Int>()
    val other = ArrayList<String>()
    repeat(N) {
        val line = reader.readLine()
        if (line.startsWith("boj.kr/"))
            boj.add(line.split("/")[1].toInt())
        else
            other.add(line)
    }
    writer.write(other.sortedWith { o1, o2 ->
        if (o1.length == o2.length)
            o1.compareTo(o2)
        else
            o1.length - o2.length
    }.joinToString("\n"))
    if (other.isNotEmpty())
        writer.newLine()
    writer.write(boj.sorted().joinToString("\n") { "boj.kr/${it}" })
    writer.flush()
}
    