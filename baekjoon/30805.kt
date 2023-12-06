import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Arrays
import java.util.StringTokenizer
import java.util.TreeMap
import kotlin.math.*

fun indexOf(arr: Array<Int>, startIdx: Int, target: Int): Int {
    var idx = startIdx
    while (idx < arr.size && arr[idx] != target) {
        idx++
    }
    return idx
}

fun solve(arrA: Array<Int>, arrB: Array<Int>, result: ArrayList<Int>, aIdx: Int, bIdx: Int) {
    if (aIdx >= arrA.size || bIdx >= arrB.size)
        return
    var nextAIdx = arrA.size
    var nextBIdx = arrB.size
    for (i in 100 downTo 1) {
        val a = indexOf(arrA, aIdx, i)
        if (a < arrA.size) {
            val b = indexOf(arrB, bIdx, i)
            if (b < arrB.size) {
                result.add(i)
                nextAIdx = a + 1
                nextBIdx = b + 1
                break
            }
        }
    }
    solve(arrA, arrB, result, nextAIdx, nextBIdx)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arrA = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val M = reader.readLine().toInt()
    val arrB = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val result = ArrayList<Int>()
    solve(arrA, arrB, result, 0, 0)
    writer.write("${result.size}\n")
    writer.write(result.joinToString(" "))
    writer.flush()
}