import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var count: Int = 1
var result: Int = -1
var target: Int = 0
fun mergeSort(arr: Array<Int>, p: Int, r: Int) {
    if (p < r) {
        val q = (p + r) / 2
        mergeSort(arr, p, q)
        mergeSort(arr, q + 1, r)
        merge(arr, p, q, r)
    }
}

fun merge(arr: Array<Int>, p: Int, q: Int, r: Int) {
    var i = p
    var j = q + 1
    val temp = ArrayList<Int>()
    while (i <= q && j <= r) {
        if (arr[i] <= arr[j]) {
            temp.add(arr[i++])
        } else {
            temp.add(arr[j++])
        }
    }
    while (i <= q) {
        temp.add(arr[i++])
    }
    while (j <= r) {
        temp.add(arr[j++])
    }
    i = p
    var t = 0
    while (i <= r) {
        if (count == target)
            result = temp[t]
        count++
        arr[i++] = temp[t++]
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    target = K
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    mergeSort(arr, 0, N - 1)
    writer.write("${result}\n")
    writer.flush()
}