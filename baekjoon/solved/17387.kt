package `17387`

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

    fun between(s: Point, e: Point): Boolean {
        return ((s.x <= x && x <= e.x) || (e.x <= x && x <= s.x)) &&
                ((s.y <= y && y <= e.y) || (e.y <= y && y <= s.y))

    }
}

data class Line(val p1: Point, val p2: Point, val vec: Point = p2 - p1) {

    fun isCross(other: Line): Boolean {
        if (vec.isCross(other.p1 - p2, other.p2 - p2) &&
            other.vec.isCross(p1 - other.p2, p2 - other.p2)
        )
            return true
        if ((vec X (other.p1 - p2)) == 0L && other.p1.between(p1, p2) ||
            (vec X (other.p2 - p2)) == 0L && other.p2.between(p1, p2) ||
            (other.vec X (p1 - other.p2)) == 0L && p1.between(other.p1, other.p2) ||
            (other.vec X (p2 - other.p2)) == 0L && p2.between(other.p1, other.p2)
        )
            return true
        return false
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
    val line1 = Line(p1, p2)
    // p3->p4
    val line2 = Line(p3, p4)
    println(if (line1.isCross(line2)) "1" else "0")
}