package `3482`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.StringTokenizer

data class Data(val x: Int, val y: Int, val depth: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

class Solution(
    private val reader: BufferedReader,
    private val writer: BufferedWriter
) {

    val C: Int
    val R: Int
    val map: Array<Array<Int>>

    init {
        val stringTokenizer = StringTokenizer(reader.readLine())
        C = stringTokenizer.nextToken().toInt()
        R = stringTokenizer.nextToken().toInt()
        map = Array<Array<Int>>(R) { Array(C) { 0 } }

        for (y in 0 until R) {
            val line = reader.readLine()
            for (x in 0 until C) {
                map[y][x] = if (line[x] == '#') 0 else 1
            }
        }
    }

    constructor(inputStream: InputStream, outputStream: OutputStream) : this(
        BufferedReader(
            InputStreamReader(
                inputStream
            )
        ), BufferedWriter(OutputStreamWriter(outputStream))
    )

    fun solve() {
        var startBlock = findFreeBlock()
        startBlock = findFurthestPoint(2, startBlock.x, startBlock.y, 0)
        startBlock = findFurthestPoint(3, startBlock.x, startBlock.y, 0)
        writer.write("Maximum rope length is ${startBlock.depth}.\n")
    }

    private fun findFreeBlock(): Data {
        for (y in 0 until R) {
            for (x in 0 until C) {
                if (map[y][x] != 0)
                    return Data(x, y, 0)
            }
        }
        return Data(0, 0, 0)
    }

    private fun findFurthestPoint(token: Int, x: Int, y: Int, depth: Int): Data {
        var maxData = Data(x, y, depth)
        repeat(4) {
            val nextX = dx[it] + x
            val nextY = dy[it] + y
            if (nextX in 0 until C && nextY in 0 until R &&
                map[nextY][nextX] != 0 && map[nextY][nextX] != token
            ) {
                map[nextY][nextX] = token
                val data = findFurthestPoint(token, nextX, nextY, depth + 1)
                if (data.depth > maxData.depth)
                    maxData = data
            }
        }
        return maxData
    }
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        Solution(reader, writer).solve()
    }
    writer.flush()
}