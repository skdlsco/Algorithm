package `4105`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
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

    override fun toString(): String {
        return "${x} ${y}"
    }

    fun toString(precision: Int): String {
        return "%.${precision}f %.${precision}f".format(x, y)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val input = reader.readLine().split(" ").map { it.toDouble() }
        val OA = Point(input[0], input[1])
        val OB = Point(input[2], input[3])
        val OC = Point(input[4], input[5])
        val OD = Point(input[6], input[7])
        val OE = Point(input[8], input[9])
        val OF = Point(input[10], input[11])

        if (input.all { it == 0.0 })
            break
        // 삼각형 DEF의 넓이를 구하자
        // DF와 DE 만들기
        val DF = OF - OD
        val DE = OE - OD
        val rectangleArea = (DF X DE) / 2

        val AB = OB - OA
        val AC = OC - OA
        // AH = aAC
        val a = abs((rectangleArea) / (AC X AB))
        val AH = AC * a
        val OH = AH + OA
        val OG = OH + AB
        writer.write("${OG.toString(3)} ${OH.toString(3)}\n")
    }
    writer.flush()
}