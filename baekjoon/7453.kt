import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun lowerBound(list: List<Long>, target: Long): Long {
    var left = 0
    var right = list.size - 1
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] < target)
            left = mid + 1
        else
            right = mid
    }
    return left.toLong()
}


fun upperBound(list: List<Long>, target: Long): Long {
    var left = 0
    var right = list.size - 1
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] <= target)
            left = mid + 1
        else
            right = mid
    }
    return left.toLong()
}


fun main() {
    val N = reader.readLine().toInt()
    val arr = Array<Array<Long>>(N) { Array(4) { 0 } }
    repeat(N) {
        arr[it] = reader.readLine().split(" ").map { it.toLong() }.toTypedArray()
    }
    val groupA = LongArray(N * N)
    val groupB = LongArray(N * N)
    for (i in 0 until N) {
        for (j in 0 until N) {
            val numA = arr[i][2] + arr[j][3]
            val numB = arr[i][0] + arr[j][1]
            groupA[i * N + j] = numA
            groupB[i * N + j] = numB
        }
    }
    groupA.sort()
    groupB.sort()
    var left = 0
    var right = groupB.size - 1
    var sum = 0L
    while (left < groupA.size && right >= 0) {
        val numA = groupA[left]
        val numB = groupB[right]

        val num = numA + numB
        if (num < 0L) {
            left++
        } else if (num > 0L) {
            right--
        } else {
            var leftCount = 0L
            var rightCount = 0L
            while (left < groupA.size) {
                if (groupA[left] == numA)
                    leftCount++
                else
                    break
                left++
            }
            while (right >= 0) {
                if (groupB[right] == numB)
                    rightCount++
                else
                    break
                right--
            }
            sum += leftCount * rightCount
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
