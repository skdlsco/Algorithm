package `14395`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.TreeSet

fun find(s: Long, t: Long): String {
    if (t == 0L)
        return "-"
    val visited = TreeSet<Long>()
    val queue = LinkedList<Pair<String, Long>>()
    visited.add(s)
    queue.add(Pair("", s))
    while (queue.isNotEmpty()) {
        val (str, cur) = queue.pop()

        if (cur == t)
            return str
        if (cur * cur <= t && !visited.contains(cur * cur)) {
            queue.add(Pair("$str*", cur * cur))
            visited.add(cur * cur)
        }
        if (cur + cur <= t && !visited.contains(cur + cur)) {
            queue.add(Pair("$str+", cur + cur))
            visited.add(cur + cur)
        }
        if (cur != 0L && !visited.contains(1)) {
            queue.add(Pair("$str/", 1))
            visited.add(1)
        }
    }
    return "-1"
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (s, t) = reader.readLine().split(" ").map { it.toLong() }

    if (s == t) {
        println(0)
    } else {
        println(find(s, t))
    }
}