package `17359`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Data(
    val first: Char,
    val last: Char,
    val changeCnt: Int
) {
    constructor(s: String) : this(s.first(), s.last(), getStatusChangeCnt(s)) {}
}

fun getStatusChangeCnt(s: String): Int {
    var prev = s[0]
    var cnt = 0
    s.forEach {
        if (prev != it)
            cnt++
        prev = it
    }
    return cnt
}

fun isVisited(visited: Array<Int>, depth: Int, now: Int): Boolean {
    return (0 until depth).any {
        visited[it] == now
    }
}

fun solve(N: Int, input: Array<Data>, visited: Array<Int>, depth: Int): Int {
    if (depth == N) {
        var prev = input[visited[0]].last
        var sum = input[visited[0]].changeCnt
        for (i in 1 until N) {
            val data = input[visited[i]]
            if (prev != data.first) {
                sum++
            }
            sum += data.changeCnt
            prev = data.last
        }
        return sum
    }
    return (0 until N).minOf { now ->
        if (!isVisited(visited, depth, now)) {
            visited[depth] = now
            solve(N, input, visited, depth + 1)
        } else Int.MAX_VALUE
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val N = reader.readLine().toInt()
    val input = Array<Data>(N) { Data(reader.readLine()) }
    val visited = Array<Int>(N) { 0 }


    println(solve(N, input, visited, 0))
}