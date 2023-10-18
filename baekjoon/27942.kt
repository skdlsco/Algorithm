package `27942`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val d = arrayOf(arrayOf(-1, 0, 0, 0), arrayOf(0, 1, 0, 0), arrayOf(0, 0, -1, 0), arrayOf(0, 0, 0, 1))
val direction = arrayOf("U", "D", "L", "R")

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val prefixSum = Array<Array<Int>>(N + 1) { Array(N + 1) { 0 } }
    for (y in 1..N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (x in 1..N) {
            prefixSum[y][x] =
                prefixSum[y - 1][x] + prefixSum[y][x - 1] - prefixSum[y - 1][x - 1] + stringTokenizer.nextToken()
                    .toInt()
        }
    }
    var sum = 0
    // top, bottom, left, right
    val plant = arrayOf(N / 2, N / 2 + 1, N / 2,  N / 2 + 1)
    val plantSum = Array<Int>(4) { 0 }
    val path = StringBuilder()
    while (true) {
        for (i in 0 until 4) {
            val top = plant[0] + d[i][0]
            val bottom = plant[1] + d[i][1]
            val left = plant[2] + d[i][2]
            val right = plant[3] + d[i][3]
            if (left in 1..N && top in 1..N && right in 1..N && bottom in 1..N)
                plantSum[i] =
                    prefixSum[top - 1][left - 1] + prefixSum[bottom][right] - prefixSum[bottom][left - 1] - prefixSum[top - 1][right] - sum
            else
                plantSum[i] = 0
        }
        val maxValue = plantSum.maxOf { it }
        val maxIdx = plantSum.indexOf(maxValue)
        if (maxValue <= 0)
            break
        sum += maxValue
        path.append(direction[maxIdx])
        for (i in 0 until 4) {
            plant[i] += d[maxIdx][i]
        }
    }
    writer.write("${sum}\n")
    writer.write(path.toString())
    writer.flush()
}