package `1269`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B) = reader.readLine().split(" ").map { it.toInt() }
    val setA = HashSet<Int>()
    val setB = HashSet<Int>()

    reader.readLine().split(" ").forEach { setA.add(it.toInt()) }
    reader.readLine().split(" ").forEach { setB.add(it.toInt()) }

    val unionCount = setA.union(setB).count()
    val intersectCount = setA.intersect(setB).count()

    println(unionCount - intersectCount)
}