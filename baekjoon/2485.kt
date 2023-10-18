package `2485`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N) { reader.readLine().toInt() }
    arr.sort()
    val distanceArr = Array<Int>(N - 1) {
        arr[it + 1] - arr[it]
    }
    distanceArr.sort()
    var distance = distanceArr[0]
    for (i in 1 until distanceArr.size) {
        distance = gcd(distance, distanceArr[i])
        if (distance == 1)
            break
    }
    val result = distanceArr.sumOf { it / distance - 1 }
    writer.write("${result}")
    writer.flush()
}