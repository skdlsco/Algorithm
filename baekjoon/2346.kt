package `2346`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val N = reader.readLine().toInt()
    var cur = 0
    val deque = ArrayDeque(reader.readLine().split(" ").mapIndexed { index, s -> Pair(index + 1, s.toInt()) })
    while (deque.isNotEmpty()) {
        val rotate = deque[cur].second
        writer.write("${deque.removeAt(cur).first} ")
        if (deque.size > 0)
            cur = (cur + rotate + (if (rotate > 0) -1 else 0)) % deque.size
        if (cur < 0)
            cur += deque.size
    }
    writer.flush()
}