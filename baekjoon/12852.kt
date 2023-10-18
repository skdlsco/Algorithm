package `12852`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val X = reader.readLine().toInt()
    val visited = Array<Int>(X + 1) { 0 }
    val queue = LinkedList<Int>()
    queue.add(X)
    visited[X] = X
    while (queue.isNotEmpty() && visited[1] == 0) {
        val cur = queue.remove()
        if (cur % 3 == 0 && visited[cur / 3] == 0) {
            queue.add(cur / 3)
            visited[cur / 3] = cur
        }
        if (cur % 2 == 0 && visited[cur / 2] == 0) {
            queue.add(cur / 2)
            visited[cur / 2] = cur
        }
        if (visited[cur - 1] == 0) {
            queue.add(cur - 1)
            visited[cur - 1] = cur
        }
    }

    val path = ArrayList<Int>()
    var cur = 1
    var cnt = 0
    while (cur != X) {
        path.add(cur)
        cur = visited[cur]
        cnt++
    }
    path.add(cur)
    path.reverse()
    println(cnt)
    println(path.joinToString(" "))
}