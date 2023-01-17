import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val graph = Array<Array<Boolean>>(N) { reader.readLine().split(" ").map { it == "1" }.toTypedArray() }

    (0 until N).forEach { mid ->
        (0 until N).forEach { start ->
            (0 until N).forEach { end ->
                if (graph[start][mid] && graph[mid][end])
                    graph[start][end] = true
            }
        }
    }
    graph.forEach {
        it.forEach { b ->
            print("${if (b) 1 else 0} ")
        }
        println()
    }
}