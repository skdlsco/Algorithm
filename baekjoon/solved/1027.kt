package `1027`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val buildingArr = reader.readLine().split(" ").map { it.toDouble() }
    var result = 0
    for (cur in 0 until N) {
        var count = 0
        // left
        for (left in 0 until cur) {
            val gradient =  (buildingArr[cur] - buildingArr[left]) / (cur - left)
            var isVisible = true
            for (mid in left + 1 until cur) {
                val y = gradient * (mid - cur) + buildingArr[cur]
                if (y <= buildingArr[mid]) {
                    isVisible = false
                    break
                }
            }
            if (isVisible)
                count++
        }

        // right
        for (right in cur + 1 until N) {
            val gradient = (buildingArr[cur] - buildingArr[right]) / (cur - right)
            var isVisible = true
            for (mid in cur + 1 until right) {
                val y = gradient * (mid - cur) + buildingArr[cur]
                if (y <= buildingArr[mid])
                    isVisible = false
            }
            if (isVisible)
                count++
        }
        result = max(result, count)
    }
    println(result)
}