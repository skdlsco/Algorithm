package `29753`

import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

val scoreMap = HashMap<String, Int>().apply {
    put("A+", 450)
    put("A0", 400)
    put("B+", 350)
    put("B0", 300)
    put("C+", 250)
    put("C0", 200)
    put("D+", 150)
    put("D0", 100)
    put("F", 0)
}

fun main() {
    val reader = Scanner(InputStreamReader(System.`in`))
    val (n, x) = reader.nextLine().split(" ")
    val N = n.toInt()
    val temp = x.split(".")
    val X = temp[0].toInt() * 100 + temp[1].toInt()
    var timeSum = 0
    var scoreSum = 0
    repeat(N - 1) {
        val time = reader.nextInt()
        val score = scoreMap[reader.next()]!!
        timeSum += time
        scoreSum += score * time
    }
    val time = reader.nextInt()
    timeSum += time
    for (score in scoreMap.toList().sortedBy { it.second }) {
        if (X < (scoreSum + score.second * time) / timeSum) {
            println(score.first)
            return
        }
    }
    println("impossible")
}
    