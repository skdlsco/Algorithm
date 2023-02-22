package `14908`

import java.io.BufferedReader
import java.io.InputStreamReader

data class Job(val id: Int, val data: Double)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    // 소요시간, 보상금
    val jobs = ArrayList<Job>(N)
    repeat(N) {
        val (t, s) = reader.readLine().split(" ").map { it.toDouble() }
        jobs.add(Job(it + 1, t / s))
    }
    jobs.sortBy { it.data }
    println(jobs.joinToString(" ") { "${it.id}" })
}