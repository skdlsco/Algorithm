import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import kotlin.math.max

class `14500` {
    data class Point(val x: Int, val y: Int)

    val tetrominos = Array<Array<Point>>(19) { Array(0) { Point(0, 0) } }

    init {
        // i
        tetrominos[0] = arrayOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0))
        tetrominos[1] = arrayOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3))

        //o
        tetrominos[2] = arrayOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1))

        // s z
        tetrominos[3] = arrayOf(Point(0, 0), Point(0, 1), Point(1, 1), Point(1, 2))
        tetrominos[4] = arrayOf(Point(0, 0), Point(1, 0), Point(1, -1), Point(2, -1))
        tetrominos[5] = arrayOf(Point(0, 0), Point(0, 1), Point(-1, 1), Point(-1, 2))
        tetrominos[6] = arrayOf(Point(0, 0), Point(1, 0), Point(1, 1), Point(2, 1))

        // t
        tetrominos[7] = arrayOf(Point(0, 0), Point(1, 0), Point(1, 1), Point(2, 0))
        tetrominos[8] = arrayOf(Point(0, 0), Point(1, 0), Point(1, -1), Point(2, 0))
        tetrominos[9] = arrayOf(Point(0, 0), Point(0, 1), Point(-1, 1), Point(0, 2))
        tetrominos[10] = arrayOf(Point(0, 0), Point(0, 1), Point(1, 1), Point(0, 2))

        // l
        tetrominos[11] = arrayOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(1, 2))
        tetrominos[12] = arrayOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, -1))
        tetrominos[13] = arrayOf(Point(0, 0), Point(1, 0), Point(1, 1), Point(1, 2))
        tetrominos[14] = arrayOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(2, 0))

        // j
        tetrominos[15] = arrayOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(-1, 2))
        tetrominos[16] = arrayOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1))
        tetrominos[17] = arrayOf(Point(0, 0), Point(1, 0), Point(0, 1), Point(0, 2))
        tetrominos[18] = arrayOf(Point(0, 0), Point(0, 1), Point(1, 1), Point(2, 1))
    }

    fun getMax(arr: Array<Array<Int>>, N: Int, M: Int): Int {
        return (0 until N).maxOf { y ->
            (0 until M).maxOf { x ->
                tetrominos.maxOf {
                    var sum = 0
                    try {
                        it.sumOf {
                            arr[y + it.y][x + it.x]
                        }
                    } catch (e: Exception) {
                        0
                    }
                }
            }
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    val checker = `14500`()

    println(checker.getMax(arr, N, M))
}