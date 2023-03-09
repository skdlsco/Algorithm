package `17386`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.sign

data class Point(val x: Long, val y: Long) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(a: Long): Point {
        return Point(x * a, y * a)
    }

    infix fun wedgeProduct(other: Point): Long {
        return x * other.y - y * other.x
    }

    infix fun X(other: Point): Long {
        return wedgeProduct(other)
    }

    fun isCross(p1: Point, p2: Point): Boolean {
        return (this X p1).sign * (this X p2).sign < 0
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var stringTokenizer = StringTokenizer(reader.readLine())
    val p1 = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    val p2 = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    stringTokenizer = StringTokenizer(reader.readLine())
    val p3 = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    val p4 = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())

    // p1->p2
    val line1 = p2 - p1
    // p3->p4
    val line2 = p4 - p3
    val isCross = line1.isCross(p3 - p2, p4 - p2) && line2.isCross(p1 - p4, p2 - p4)
    println(if (isCross) "1" else "0")
}