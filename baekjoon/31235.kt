package I

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun solve(A: Array<Int>, k: Int): Boolean {
    var targetIdx = 0
    for (i in 1 until A.size) {
        if (A[i] >= A[targetIdx])
            targetIdx = i
        if (targetIdx + k <= i)
            return false
    }
    return true
}

fun main() {
    val N = reader.readLine().toInt()
    val A = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    var left = 1
    var right = N
    while (left < right) {
        val mid = (left + right) / 2
        if (solve(A, mid)) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    writer.write("${left}")
    writer.flush()
}
    