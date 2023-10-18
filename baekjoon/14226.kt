package `14226`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

data class Data(val emoji: Int, val clipboard: Int, val turn: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val S = reader.readLine().toInt()
    val visited = Array<Array<Boolean>>(2001) { Array(2001) { false } }
    val queue = LinkedList<Data>()

    queue.add(Data(1, 0, 0))
    visited[1][0] = true
    while (queue.isNotEmpty()) {
        val (emoji, clipboard, turn) = queue.pop()
        if (emoji == S) {
            println(turn)
            break
        }

        if (clipboard != 0 && emoji + clipboard < 2001 &&
            !visited[emoji + clipboard][clipboard]
        ) {
            queue.add(Data(emoji + clipboard, clipboard, turn + 1))
            visited[emoji + clipboard][clipboard] = true
        }
        if (emoji > 0 && !visited[emoji - 1][clipboard]) {
            queue.add(Data(emoji - 1, clipboard, turn + 1))
            visited[emoji - 1][clipboard] = true
        }
        if (!visited[emoji][emoji]) {
            queue.add(Data(emoji, emoji, turn + 1))
            visited[emoji][emoji] = true
        }
    }
}