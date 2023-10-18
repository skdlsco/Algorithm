package `28455`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = (0 until N).map { reader.readLine().toInt() }.sortedDescending()
    var levelSum = 0
    var abilitySum = 0
    for (i in 0 until min(N, 42)) {
        levelSum += arr[i]
        val ability = if (arr[i] >= 250)
            5
        else if (arr[i] >= 200)
            4
        else if (arr[i] >= 140)
            3
        else if (arr[i] >= 100)
            2
        else if (arr[i] >= 60)
            1
        else 0
        abilitySum += ability
    }
    writer.write("${levelSum} ${abilitySum}\n")
    writer.flush()
}