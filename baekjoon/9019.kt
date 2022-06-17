import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val visited = Array(10000) { Pair(-1, '0') }
        val queue = LinkedList<Int>()
        val (start, end) = reader.readLine().split(" ").map { it.toInt() }
        visited[start] = Pair(start, '0')
        queue.push(start)
        while (queue.isNotEmpty()) {
            val now = queue.removeLast()
            if (now == end)
                break
            //D
            var D = now * 2
            if (D > 9999)
                D %= 10000
            if (visited[D].first == -1) {
                visited[D] = Pair(now, 'D')
                queue.push(D)
            }
            //S
            var S = now - 1
            if (S < 0)
                S = 9999
            if (visited[S].first == -1) {
                visited[S] = Pair(now, 'S')
                queue.push(S)
            }
            // L
            val L = (now / 1000) + (((now / 100) % 10) * 1000) + (((now / 10) % 10) * 100) + ((now % 10) * 10)
            if (visited[L].first == -1) {
                visited[L] = Pair(now, 'L')
                queue.push(L)
            }
            // R
            val R = ((now / 1000) * 100) + (((now / 100) % 10) * 10) + ((now / 10) % 10) + ((now % 10) * 1000)
            if (visited[R].first == -1) {
                visited[R] = Pair(now, 'R')
                queue.push(R)
            }
        }

        var now = end
        val stringBuilder = StringBuilder()
        while (now != start) {
            stringBuilder.append(visited[now].second)
            now = visited[now].first
        }
        writer.write(stringBuilder.toString().reversed())
        writer.newLine()
    }
    writer.flush()
}