package `2800`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))
val visited = Array<Boolean>(200) { false }
val result = HashSet<String>()

fun appendAllCase(
    line: String,
    brackets: List<Pair<Int, Int>>,
    pos: Int,
    removed: Boolean
) {
    if (pos == brackets.size) {
        if (!removed)
            return
        val removedLine = line.filterIndexed { index, c -> !visited[index] }
        result.add(removedLine)
        return
    }
    appendAllCase(line, brackets, pos + 1, removed)
    val (start, end) = brackets[pos]
    visited[start] = true
    visited[end] = true
    appendAllCase(line, brackets, pos + 1, true)
    visited[start] = false
    visited[end] = false
}

fun main() {
    val line = reader.readLine()
    // start, end
    val brackets = ArrayList<Pair<Int, Int>>()
    // position
    val stack = Stack<Int>()
    for (i in line.indices) {
        if (line[i] == '(')
            stack.add(i)
        else if (line[i] == ')') {
            val start = stack.pop()
            brackets.add(Pair(start, i))
        }
    }
    appendAllCase(line, brackets, 0, false)
    result.sorted().forEach {
        writer.write(it)
        writer.newLine()
    }
    writer.flush()
}
