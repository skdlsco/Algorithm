package `20149`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

const val EPSILON = 0.000001

data class Vector2(val x: Double, val y: Double) {
    constructor(x: Long, y: Long) : this(x.toDouble(), y.toDouble())

    operator fun plus(other: Vector2): Vector2 {
        return Vector2(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector2): Vector2 {
        return Vector2(x - other.x, y - other.y)
    }

    operator fun times(a: Double): Vector2 {
        return Vector2(x * a, y * a)
    }

    operator fun compareTo(other: Vector2): Int {
        if (equals(other))
            return 0
        return if (x == other.x)
            y.compareTo(other.y)
        else
            x.compareTo(other.x)
    }

    infix fun dot(other: Vector2): Double {
        return x * other.x + y * other.y
    }

    infix fun cross(other: Vector2): Double {
        return x * other.y - y * other.x
    }

    override fun toString(): String {
        return "${x} ${y}"
    }
}

fun ccw(a: Vector2, b: Vector2): Double {
    return a.cross(b)
}

fun ccw(p: Vector2, a: Vector2, b: Vector2): Double {
    return ccw(a - p, b - p)
}

fun min(a: Vector2, b: Vector2): Vector2 {
    return if (a < b) a
    else b
}

fun max(a: Vector2, b: Vector2): Vector2 {
    return if (a > b) a
    else b
}

fun segmentIntersects(
    a: Vector2, b: Vector2,
    c: Vector2, d: Vector2
): Boolean {
    val ab = ccw(a, b, c) * ccw(a, b, d)
    val cd = ccw(c, d, a) * ccw(c, d, b)
    // 두선분이 한 직선위 or 끝점이 겹치는 경우
    if (ab == 0.0 && cd == 0.0) {
        return !(max(a, b) < min(c, d) || max(c, d) < min(a, b))
    }
    return ab <= 0 && cd <= 0
}

fun lineIntersection(
    a: Vector2, b: Vector2,
    c: Vector2, d: Vector2
): Vector2? {
    val det = (b - a).cross(d - c)
    if (abs(det) < EPSILON)
        return null
    return a + (b - a) * ((c - a).cross(d - c) / det)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var stringTokenizer = StringTokenizer(reader.readLine())
    val a = Vector2(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    val b = Vector2(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    stringTokenizer = StringTokenizer(reader.readLine())
    val c = Vector2(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())
    val d = Vector2(stringTokenizer.nextToken().toLong(), stringTokenizer.nextToken().toLong())

    val isIntersects = segmentIntersects(a, b, c, d)
    if (isIntersects) {
        println("1")
        var p = lineIntersection(a, b, c, d)
        // 한 직선위에서 만나는 경우
        if (p == null) {
            // 한 점에서만 만나야 한다.
            if (min(a, b) == max(c, d))
                p = min(a, b)
            else if (max(a, b) == min(c, d))
                p = min(c, d)
        }
        if (p != null)
            println("${p.x} ${p.y}")
    } else
        println("0")
}