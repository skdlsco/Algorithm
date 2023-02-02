package `11725`
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val tree = Array<LinkedList<Int>>(N) { LinkedList() }
    val parentArray = Array<Int>(N) { -1 }
    repeat(N - 1) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() - 1 }
        tree[a].add(b)
        tree[b].add(a)
    }
    val queue = LinkedList<Int>()
    queue.push(0)
    parentArray[0] = 0
    while (queue.isNotEmpty()) {
        val parent = queue.pop()
        tree[parent].forEach {
            if (parentArray[it] == -1) {
                parentArray[it] = parent
                queue.push(it)
            }
        }
    }
    parentArray.sliceArray(1 until N).forEach {
        writer.write("${it + 1}\n")
    }
    writer.flush()
}