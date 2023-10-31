import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Pos(val x: Double, val y: Double) {
    val slope = y / x
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = ArrayList<Pair<Pos, Int>>()
    repeat(N) {
        val (xbl, ybl, xtr, ytr) = reader.readLine().split(" ").map { it.toDouble() }
        val leftTop = Pos(xbl, ytr)
        val rightBottom = Pos(xtr, ybl)
        arr.add(Pair(leftTop, 1))
        arr.add(Pair(rightBottom, -1))
    }
    arr.sortWith() { o1, o2 ->
        if (o1.first.slope - o2.first.slope < 0.00000001) {
            if (o1.second < o2.second)
                1
            else
                -1
        } else {
            if (o1.first.slope < o2.first.slope)
                1
            else -1
        }
    }
    arr.sortByDescending { it.first.y / it.first.x }
    var cnt = 0
    var ans = 0
    for ((_, v) in arr) {
        cnt += v
        ans = maxOf(ans, cnt)
    }
    writer.write("${ans}\n")
    writer.flush()
}