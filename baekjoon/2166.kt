package `2166`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

data class Point(val x: Double, val y: Double) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(a: Double): Point {
        return Point(x * a, y * a)
    }

    infix fun wedgeProduct(other: Point): Double {
        return x * other.y - y * other.x
    }

    infix fun X(other: Point): Double {
        return wedgeProduct(other)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val N = reader.readLine().toInt()
    var stringTokenizer = StringTokenizer(reader.readLine())
    var sum = 0.0
    val primary = Point(stringTokenizer.nextToken().toDouble(), stringTokenizer.nextToken().toDouble())
    stringTokenizer = StringTokenizer(reader.readLine())
    var prev = Point(stringTokenizer.nextToken().toDouble(), stringTokenizer.nextToken().toDouble())
    repeat(N - 2) {
        stringTokenizer = StringTokenizer(reader.readLine())
        val p = Point(stringTokenizer.nextToken().toDouble(), stringTokenizer.nextToken().toDouble())
        // primary -> prev -> p
         sum += (prev - primary) X (p - prev)
        prev = p
    }
    println("%.1f".format(abs(sum / 2)))
}