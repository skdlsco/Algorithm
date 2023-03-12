package `6439`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min
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

fun isCrossOrInRect(lineStart: Point, lineEnd: Point, leftTop: Point, rightBottom: Point): Boolean {
    val line = Line(lineStart, lineEnd)
    val rightTop = Point(rightBottom.x, leftTop.y)
    val leftBottom = Point(leftTop.x, rightBottom.y)
    val top = Line(leftTop, rightTop)
    val right = Line(rightTop, rightBottom)
    val bottom = Line(rightBottom, leftBottom)
    val left = Line(leftBottom, leftTop)

    // inRect
    if (lineStart.between(leftTop, rightBottom) && lineEnd.between(leftTop, rightBottom))
        return true
    if (line.isCross(top) || line.isCross(right) || line.isCross(bottom) || line.isCross(left))
        return true
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val lineStart = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
        val lineEnd = Point(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
        val x1 = stringTokenizer.nextToken().toLong()
        val y1 =stringTokenizer.nextToken().toLong()
        val x2 = stringTokenizer.nextToken().toLong()
        val y2 = stringTokenizer.nextToken().toLong()
        val leftTop = Point(min(x1, x2), max(y1, y2))
        val rightBottom = Point(max(x1, x2), min(y1, y2))
        if (isCrossOrInRect(lineStart, lineEnd, leftTop, rightBottom))
            writer.write("T\n")
        else writer.write("F\n")
    }
    writer.flush()
}