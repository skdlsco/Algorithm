package `16927`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.min

fun splitArray(N: Int, M: Int, arr: Array<Array<Int>>): Array<Array<Int>> {
    val lineCount = min(N, M) / 2
    val lines = Array<Array<Int>>(lineCount) { arrayOf(0) }
    // i in 0 until lineCount
    for (i in 0 until lineCount) {
        val line = ArrayList<Int>()
        // left
        var x = i
        for (y in i until N - i) {
            line.add(arr[y][x])
        }
        // bottom
        var y = N - i - 1
        for (x in i + 1 until M - i) {
            line.add(arr[y][x])
        }
        // right
        x = M - i - 1
        for (y in N - 2 - i downTo i) {
            line.add(arr[y][x])
        }
        // top
        y = i
        for (x in M - 2 - i downTo i + 1) {
            line.add(arr[y][x])
        }
        lines[i] = line.toTypedArray()
    }
    return lines
}

fun rotateLines(lines: Array<Array<Int>>, R: Int) {
    for (i in lines.indices) {
        val line = lines[i]
        val size = line.size
        val rotate = R % size
        val newLine = Array<Int>(size) {
            line[(size + it - rotate) % size]
        }
        lines[i] = newLine
    }
}

fun assignToArr(lines: Array<Array<Int>>, N: Int, M: Int, arr: Array<Array<Int>>) {
    val lineCount = min(N, M) / 2
    // i in 0 until lineCount
    for (i in 0 until lineCount) {
        var lineIdx = 0
        // left
        var x = i
        for (y in i until N - i) {
            arr[y][x] = lines[i][lineIdx]
            lineIdx++
        }
        // bottom
        var y = N - i - 1
        for (x in i + 1 until M - i) {
            arr[y][x] = lines[i][lineIdx]
            lineIdx++
        }
        // right
        x = M - i - 1
        for (y in N - 2 - i downTo i) {
            arr[y][x] = lines[i][lineIdx]
            lineIdx++
        }
        // top
        y = i
        for (x in M - 2 - i downTo i + 1) {
            arr[y][x] = lines[i][lineIdx]
            lineIdx++
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, R) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { Array(M) { 0 } }

    for (y in 0 until N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (x in 0 until M) {
            arr[y][x] = stringTokenizer.nextToken().toInt()
        }
    }

    // split
    val lines = splitArray(N, M, arr)
    // rotate
    rotateLines(lines, R)
    // assign
    assignToArr(lines, N, M, arr)

    writer.write(arr.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}