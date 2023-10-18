package `14499`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Dice(
    val vertical: Array<Int> = Array<Int>(4) { 0 },
    val horizontal: Array<Int> = Array<Int>(4) { 0 }
) {
    val top: Int
        get() = vertical[0]
    val bottom: Int
        get() = vertical[2]

    fun setBottom(value: Int) {
        if (value == 0)
            return
        vertical[2] = value
        horizontal[2] = value
    }

    //  2
    //4 1 3
    //  5
    //  6
    // 1 3 6 4
    // 1 5 6 2
    private fun rotateRight(array: Array<Int>) {
        val temp = array[0]
        array[0] = array[1]
        array[1] = array[2]
        array[2] = array[3]
        array[3] = temp
    }

    private fun rotateLeft(array: Array<Int>) {
        val temp = array[0]
        array[0] = array[3]
        array[3] = array[2]
        array[2] = array[1]
        array[1] = temp
    }


    private fun up() {
        rotateRight(vertical)
        syncHorizontal()
    }

    private fun down() {
        rotateLeft(vertical)
        syncHorizontal()
    }

    private fun left() {
        rotateLeft(horizontal)
        syncVertical()
    }

    private fun right() {
        rotateRight(horizontal)
        syncVertical()
    }

    fun move(direct: Int) {
        when (direct) {
            1 -> {
                right()
            }

            2 -> {
                left()
            }

            3 -> {
                up()
            }

            4 -> {
                down()
            }
        }
    }

    private fun syncHorizontal() {
        horizontal[0] = vertical[0]
        horizontal[2] = vertical[2]
    }

    private fun syncVertical() {
        vertical[0] = horizontal[0]
        vertical[2] = horizontal[2]
    }
}

val dx = arrayOf(0, 1, -1, 0, 0)
val dy = arrayOf(0, 0, 0, -1, 1)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, sy, sx, K) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    val commands = reader.readLine().split(" ").map { it.toInt() }
    val dice = Dice()
    var x = sx
    var y = sy
    for (command in commands) {
        val nextX = x + dx[command]
        val nextY = y + dy[command]
        if (nextX in 0 until M && nextY in 0 until N) {
            dice.move(command)
            if (map[nextY][nextX] == 0)
                map[nextY][nextX] = dice.bottom
            else {
                dice.setBottom(map[nextY][nextX])
                map[nextY][nextX] = 0
            }
            writer.write("${dice.top}\n")
            x = nextX
            y = nextY
        }
    }
    writer.flush()
}