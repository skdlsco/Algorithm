package `16935`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

class CommandArray(
    private var height: Int,
    private var width: Int,
    private var arr: Array<Array<Int>>
) {
    fun flipVertical() {
        for (y in 0 until height / 2) {
            for (x in 0 until width) {
                val temp = arr[y][x]
                arr[y][x] = arr[height - 1 - y][x]
                arr[height - 1 - y][x] = temp
            }
        }
    }

    fun flipHorizontal() {
        for (x in 0 until width / 2) {
            for (y in 0 until height) {
                val temp = arr[y][x]
                arr[y][x] = arr[y][width - 1 - x]
                arr[y][width - 1 - x] = temp
            }
        }
    }

    fun swapWidthHeight() {
        val temp = width
        width = height
        height = temp
    }

    fun rotateRight() {
        val newArr = Array<Array<Int>>(width) { Array(height) { 0 } }
        for (oldX in 0 until width) {
            for (oldY in 0 until height) {
                newArr[oldX][oldY] = arr[height - 1 - oldY][oldX]
            }
        }
        arr = newArr
        swapWidthHeight()
    }

    fun rotateLeft() {
        val newArr = Array<Array<Int>>(width) { Array(height) { 0 } }
        for (oldX in 0 until width) {
            for (oldY in 0 until height) {
                newArr[oldX][oldY] = arr[oldY][width - 1 - oldX]
            }
        }
        arr = newArr
        swapWidthHeight()
    }

    fun rotateRightQuarter() {
        val dy = arrayOf(0, height / 2, height / 2, 0)
        val dx = arrayOf(0, 0, width / 2, width / 2)
        val quarter = Array<Array<Int>>(height / 2) { Array(width / 2) { 0 } }
        for (y in 0 until height / 2) {
            for (x in 0 until width / 2) {
                quarter[y][x] = arr[y][x]
            }
        }

        for (i in 1 until 4) {
            for (y in 0 until height / 2) {
                for (x in 0 until width / 2) {
                    arr[y + dy[i - 1]][x + dx[i - 1]] = arr[y + dy[i]][x + dx[i]]
                }
            }
        }
        for (y in 0 until height / 2) {
            for (x in 0 until width / 2) {
                arr[y][x + width / 2] = quarter[y][x]
            }
        }
    }

    fun rotateLeftQuarter() {
        val dy = arrayOf(0, 0, height / 2, height / 2)
        val dx = arrayOf(0, width / 2, width / 2, 0)
        val quarter = Array<Array<Int>>(height / 2) { Array(width / 2) { 0 } }
        for (y in 0 until height / 2) {
            for (x in 0 until width / 2) {
                quarter[y][x] = arr[y][x]
            }
        }

        for (i in 1 until 4) {
            for (y in 0 until height / 2) {
                for (x in 0 until width / 2) {
                    arr[y + dy[i - 1]][x + dx[i - 1]] = arr[y + dy[i]][x + dx[i]]
                }
            }
        }
        for (y in 0 until height / 2) {
            for (x in 0 until width / 2) {
                arr[y + height / 2][x] = quarter[y][x]
            }
        }
    }

    override fun toString(): String {
        return arr.joinToString("\n") { it.joinToString(" ") }
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
    val commands = reader.readLine().split(" ").map { it.toInt() }
    val commandArray = CommandArray(N, M, arr)
    for (command in commands) {
        when (command) {
            1 -> {
                commandArray.flipVertical()
            }

            2 -> {
                commandArray.flipHorizontal()
            }

            3 -> {
                commandArray.rotateRight()
            }

            4 -> {
                commandArray.rotateLeft()
            }

            5 -> {
                commandArray.rotateRightQuarter()
            }

            6 -> {
                commandArray.rotateLeftQuarter()
            }
        }
    }
    writer.write(commandArray.toString())
    writer.flush()
}