package `11404`
import kotlin.math.min

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val arr = Array<Array<Int>>(n) { Array(n) { 0 } }
    repeat(m) {
        val (s, e, c) = readLine()!!.split(" ").map { it.toInt() }
        if (arr[s - 1][e - 1] == 0)
            arr[s - 1][e - 1] = c
        else
            arr[s - 1][e - 1] = min(arr[s - 1][e - 1], c)
    }
    repeat(n) { mid ->
        repeat(n) { start ->
            repeat(n) { end ->
                if (arr[start][mid] != 0 && arr[mid][end] != 0 && start != end) {
                    if (arr[start][end] == 0)
                        arr[start][end] = arr[start][mid] + arr[mid][end]
                    else if (arr[start][end] > arr[start][mid] + arr[mid][end])
                        arr[start][end] = arr[start][mid] + arr[mid][end]
                }
            }
        }
    }
    println(arr.joinToString("\n") {
        it.joinToString(" ") {
            "$it"
        }
    })
}