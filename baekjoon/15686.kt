package clear

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

fun getResult(
    comb: Array<Int>,
    house: ArrayList<Pair<Int, Int>>,
    chicken: ArrayList<Pair<Int, Int>>,
    M: Int,
    i: Int,
    cnt: Int
): Int {
    if (cnt == M) {
        return getDistance(chicken.filterIndexed { index, _ -> index in comb }, house)
    }
    var minDistance = Int.MAX_VALUE
    (i until chicken.size).forEach { j ->
        comb[cnt] = j
        minDistance = min(getResult(comb, house, chicken, M, j + 1, cnt + 1), minDistance)
    }
    return (minDistance)
}

fun getDistance(chicken: List<Pair<Int, Int>>, house: ArrayList<Pair<Int, Int>>): Int {
    var sum = 0

    house.forEach { h ->
        var minDistance = Int.MAX_VALUE
        chicken.forEach { c ->
            minDistance = min(abs(h.first - c.first) + abs(h.second - c.second), minDistance)
        }
        sum += minDistance
    }
    return (sum)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    val house = ArrayList<Pair<Int, Int>>()
    val chicken = ArrayList<Pair<Int, Int>>()

    repeat(N) { y ->
        repeat(N) { x ->
            val data = scanner.nextInt()
            if (data == 1)
                house.add(Pair(y, x))
            if (data == 2)
                chicken.add(Pair(y, x))
        }
    }
    val result = getResult(Array(M) { -1 }, house, chicken, M, 0, 0)
    println(result)
}