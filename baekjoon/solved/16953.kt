import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B) = reader.readLine().split(" ").map { it.toLong() }
    val queue: Queue<Pair<Long, Long>> = LinkedList<Pair<Long, Long>>()
    var ans = -1L
    queue.add(Pair(A, 1L))

    while (queue.isNotEmpty()) {
        val (num, N) = queue.remove()
        if (num > B)
            break
        if (num == B) {
            ans = N
            break
        }
        if (num * 2 <= B)
            queue.add(Pair(num * 2, N + 1))
        if (num * 10 + 1 <= B)
            queue.add(Pair(num * 10 + 1, N + 1))
    }
    println(ans)
}