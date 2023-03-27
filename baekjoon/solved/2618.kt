package `2618`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val y: Int = 1, val x: Int = 1) {
    infix fun distance(other: Point): Int {
        return abs(y - other.y) + abs(x - other.x)
    }
}

const val MAX_VALUE = 2000000
var defaultCar1Point = Point(1, 1)
var defaultCar2Point = Point(1, 1)

fun find(dp: Array<Array<Int>>, incident: Array<Point>, car1: Int, car2: Int): Int {
    if (car1 == incident.lastIndex || car2 == incident.lastIndex)
        return 0
    if (dp[car1][car2] != MAX_VALUE)
        return dp[car1][car2]
    val nextIncidentIdx = max(car1, car2) + 1
    val nextIncident = incident[nextIncidentIdx]
    val car1Point = if (car1 == 0) defaultCar1Point else incident[car1]
    val car2Point = if (car2 == 0) defaultCar2Point else incident[car2]

    val result1 = car1Point.distance(nextIncident) + find(dp, incident, nextIncidentIdx, car2)
    val result2 = car2Point.distance(nextIncident) + find(dp, incident, car1, nextIncidentIdx)

    dp[car1][car2] = min(result1, result2)
    return dp[car1][car2]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val W = reader.readLine().toInt()
    val incident = Array<Point>(W + 1) { Point() }
    defaultCar2Point = Point(N, N)
    // dp[i][j] = cost -> car1이 i 사건에, car2가 j 사건에 있을 때 W사건까지 해결했을 때의 최소 비용
    // dp[a][b], if (a or b == W) 0
    val dp = Array<Array<Int>>(W + 1) { Array(W + 1) { MAX_VALUE } }
    // incident init
    repeat(W) {
        val (y, x) = reader.readLine().split(" ").map { it.toInt() }
        incident[it + 1] = Point(y, x)
    }
    val result = find(dp, incident, 0, 0)
    writer.write("$result\n")
    var car1 = 0
    var car2 = 0
    var w = 1
    while (w <= W) {
        val car1Point = if (car1 == 0) defaultCar1Point else incident[car1]
        val car2Point = if (car2 == 0) defaultCar2Point else incident[car2]
        if (dp[w][car2] + car1Point.distance(incident[w]) < dp[car1][w] + car2Point.distance(incident[w])) {
            car1 = w
            writer.write("1\n")
        } else {
            car2 = w
            writer.write("2\n")
        }
        w++
    }
    writer.flush()
}