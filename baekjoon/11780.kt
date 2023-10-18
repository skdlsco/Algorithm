package `11780`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

const val MAX_VALUE = 123456789
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val cost = Array<Array<Int>>(N + 1) { Array(N + 1) { MAX_VALUE } }
    val route = Array<Array<Int>>(N + 1) { Array(N + 1) { 0 } }

    for (i in 1..N) {
        cost[i][i] = 0
        route[i][i] = i
    }
    repeat(M) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val start = stringTokenizer.nextToken().toInt()
        val end = stringTokenizer.nextToken().toInt()
        val c = stringTokenizer.nextToken().toInt()
        if (cost[start][end] > c) {
            cost[start][end] = c
            route[start][end] = end
        }
    }
    reader.close()
    for (m in 1..N) {
        for (s in 1..N) {
            for (e in 1..N) {
                if (cost[s][e] > cost[s][m] + cost[m][e]) {
                    cost[s][e] = cost[s][m] + cost[m][e]
                    route[s][e] = route[s][m]
                }
            }
        }
    }

    // print cost array
    for (s in 1..N) {
        for (e in 1..N) {
            if (cost[s][e] == MAX_VALUE)
                cost[s][e] = 0
            writer.write("${cost[s][e]} ")
        }
        writer.newLine()
    }
    for (s in 1..N) {
        for (e in 1..N) {
            if (cost[s][e] == 0)
                writer.write("0")
            else {
                val routeString = StringBuilder()
                var len = 1
                var m = s
                while (m != e) {
                    routeString.append("$m ")
                    m = route[m][e]
                    len++
                }
                routeString.append("$m ")
                writer.write("$len $routeString")
            }
            writer.write("\n")
        }
    }
    writer.flush()
    writer.close()
}