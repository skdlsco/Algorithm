package `11758`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(a: Int): Point {
        return Point(x * a, y * a)
    }

    infix fun wedgeProduct(other: Point): Int {
        return x * other.y - y * other.x
    }

    infix fun X(other: Point): Int {
        return wedgeProduct(other)
    }

    override fun toString(): String {
        return "${x} ${y}"
    }

    fun toString(precision: Int): String {
        return "%.${precision}f %.${precision}f".format(x, y)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var stringTokenizer = StringTokenizer(reader.readLine())
    val p1 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    stringTokenizer = StringTokenizer(reader.readLine())
    val p2 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    stringTokenizer = StringTokenizer(reader.readLine())
    val p3 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    val result = (p2 - p1) X (p3 - p2)
    val output = when {
        result < 0 -> -1
        result > 0 -> 1
        else -> 0
    }
    println(output)
}