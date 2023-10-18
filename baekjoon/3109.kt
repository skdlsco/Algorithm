package `3109`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var R = 0
var C = 0
val map = Array<Array<Char>>(10001) { Array(501) { '.' } }
val dy = arrayOf(-1, 0, 1)
fun dfs(y: Int, x: Int): Boolean {
    if (x == C - 1)
        return true
    for (i in 0 until 3) {
        val ny = y + dy[i]
        val nx = x + 1
        if (ny in 0 until R && map[ny][nx] == '.') {
            map[ny][nx] = 'x'
            if (dfs(ny, nx))
                return true
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (r, c) = reader.readLine().split(" ").map { it.toInt() }
    R = r
    C = c
    for (y in 0 until R) {
        val line = reader.readLine()
        for (x in 0 until C) {
            map[y][x] = line[x]
        }
    }
    var cnt = 0
    for (y in 0 until R) {
        if (dfs(y, 0))
            cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}