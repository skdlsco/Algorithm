package `11650`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun listToPair(list: List<Int>): Pair<Int, Int> = Pair(list[0], list[1])

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Pair<Int, Int>>(N) { Pair(0, 0) }
    repeat(N) {
        arr[it] = listToPair(reader.readLine().split(" ").map { it.toInt() })
    }
    reader.close()
    arr.sortWith(Comparator { o1, o2 ->
        if (o1.first == o2.first)
            o1.second - o2.second
        else
            o1.first - o2.first
    })
    arr.forEach {
        writer.write(it.first.toString() + " " + it.second.toString() + "\n")
    }
    writer.flush()
    writer.close()
}