import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

object `9663` {
    fun check(y: Int, x: Int, arr: IntArray): Boolean {
        return (0 until y).all { y2 ->
            val x2 = arr[y2]
            if (x == x2)
                return false
            if (abs(y2 - y) == abs(x2 - x))
                return false
            true
        }
    }

    fun queen(N: Int, arr: IntArray, now: Int): Int {
        if (now == N)
            return 1
        return (0 until N).sumOf {
            arr[now] = it
            if (check(now, it, arr))
                queen(N, arr, now + 1)
            else 0
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    reader.close()
    println(`9663`.queen(N, IntArray(N) { -1 }, 0))
}