import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Pos(val x: Int, val y: Int) {
    infix fun distance(p: Pos) =
        abs(p.x - x) + abs(p.y - y)
}

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun check(arr: List<Pos>, pos: Array<Pos>, select: Array<Int>, depth: Int): Int {
    if (depth == arr.size) {
        return select.withIndex().sumOf { arr[it.value] distance pos[it.index] }
    }
    var ans = Int.MAX_VALUE
    for (i in arr.indices) {
        if ((0 until depth).none { select[it] == i }) {
            select[depth] = i
            ans = minOf(ans, check(arr, pos, select, depth + 1))
        }
    }
    return ans
}

fun solve(arr: List<Pos>, pos: Array<Pos>, depth: Int): Int {
    var ans = Int.MAX_VALUE
    if (depth == arr.size) {
        val selected = Array<Int>(arr.size) { -1 }
        ans = check(arr, pos, selected, 0)
    } else if (depth == 0) {
        for (y in 0 until 5) {
            for (x in 0 until 5) {
                pos[0] = Pos(x, y)
                ans = minOf(ans, solve(arr, pos, depth + 1))
            }
        }
    } else {
        for (d in 0 until 4) {
            for (i in 0 until depth) {
                val cx = pos[i].x + dx[d]
                val cy = pos[i].y + dy[d]
                if (cx in 0 until 5 && cy in 0 until 5) {
                    val next = Pos(cx, cy)
                    if ((0 until depth).none { pos[it] == next }) {
                        pos[depth] = next
                        ans = minOf(ans, solve(arr, pos, depth + 1))
                    }
                }
            }
        }
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val arr = ArrayList<Pos>()
    for (y in 0 until 5) {
        val line = reader.readLine()
        for (x in 0 until 5) {
            if (line[x] == '*') {
                arr.add(Pos(x, y))
            }
        }
    }
    val pos = Array<Pos>(arr.size) { Pos(0, 0) }
    val ans = solve(arr, pos, 0)
    writer.write("${ans}\n")
    writer.flush()
}