package `19532`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun find(nums: List<Int>): Pair<Int, Int> {
    for (x in -999..999) {
        for (y in -999..999) {
            if (nums[0] * x + nums[1] * y == nums[2] && nums[3] * x + nums[4] * y == nums[5])
                return Pair(x, y)
        }
    }
    return Pair(0, 0)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val nums = reader.readLine().split(" ").map { it.toInt() }

    val (x, y) = find(nums)
    writer.write("$x $y")
    writer.flush()
}