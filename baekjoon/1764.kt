package `1764`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<String>(N + M) { "" }
    val result = ArrayList<String>()
    repeat(N + M) {
        arr[it] = reader.readLine()
    }
    var temp = ""
    var flag = false
    arr.sort()
    arr.forEachIndexed { index, s ->
        if (temp != s) {
            if (flag)
                result.add(temp)
            temp = s
            flag = false
        }
        else {
            flag = true
        }
    }
    if (flag)
        result.add(temp)
    println(result.size)
    result.forEach {
        println(it)
    }
}