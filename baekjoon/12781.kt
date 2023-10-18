package `12781`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.sign

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

    fun isCross(p1: Point, p2: Point): Boolean {
        return (this X p1).sign * (this X p2).sign < 0
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val p1 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    val p2 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    val p3 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())
    val p4 = Point(stringTokenizer.nextToken().toInt(), stringTokenizer.nextToken().toInt())

    // p1->p2
    val line1 = p2 - p1
    println(if (line1.isCross(p3 - p2, p4 - p2)) "1" else "0")
}