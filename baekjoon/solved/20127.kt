package `20127`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.collections.ArrayList

fun validate(list: List<Int>): Boolean {
    var isInc = false
    var isDec = false
    for (i in 1 until list.size) {
        if (list[i] > list[i - 1])
            isInc = true
        if (list[i] < list[i - 1])
            isDec = true
    }
    return !(isDec && isInc)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = ArrayList<Int>()
    arr.addAll(reader.readLine().split(" ").map { it.toInt() })
    arr.add(0, arr[0])
    arr.add(arr.last())
    var point = -1
    var isInc = false
    var isDec = false
    var length = 0
    for (i in 1..N) {
        if (arr[i - 1] < arr[i]) {
            if (isDec) {
                point = i
                break
            }
            isInc = true
            length = 1
        } else if (arr[i - 1] > arr[i]) {
            if (isInc) {
                point = i
                break
            }
            isDec = true
            length = 1
        } else length++
    }
    var ans = -1
    if (point == -1) {
        ans = 0
    } else {
        for (i in arrayOf(point - length, point)) {
            val temp = arr.subList(i, arr.size) + arr.subList(0, i)
            if (validate(temp)) {
                ans = i - 1
                break
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}