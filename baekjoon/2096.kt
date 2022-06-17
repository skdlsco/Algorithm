import java.lang.Integer.min
import kotlin.math.max

fun main() {
    val N = readLine()!!.toInt()
    var maxDp = IntArray(3) { 0 }
    var minDp = IntArray(3) { 0 }
    repeat(N) {
        val arr = readLine()!!.split(" ").map { it.toInt() }
        var next = IntArray(3) { arr[it] }
        next[0] += max(maxDp[1], maxDp[0])
        next[1] += max(max(maxDp[1], maxDp[2]), maxDp[0])
        next[2] += max(maxDp[2], maxDp[1])
        maxDp = next.clone()
        next = IntArray(3) { arr[it] }
        next[0] += min(minDp[1], minDp[0])
        next[1] += min(min(minDp[1], minDp[2]), minDp[0])
        next[2] += min(minDp[2], minDp[1])
        minDp = next.clone()
    }
    println("${maxDp.maxOrNull()} ${minDp.minOrNull()}")
}