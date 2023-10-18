package `10830`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun multiply(N: Int, m1: Array<Array<Int>>, m2: Array<Array<Int>>): Array<Array<Int>> {
    val r = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (i in 0 until N) {
        for (j in 0 until N) {
            for (k in 0 until N) {
                r[i][j] = (r[i][j] + m1[i][k] * m2[k][j]) % 1000
            }
        }
    }
    return r
}

fun pow(N: Int, m: Array<Array<Int>>, b: Long): Array<Array<Int>> {
    if (b <= 1)
        return m
    if (b == 2L)
        return multiply(N, m, m)
    var temp = pow(N, m, b / 2)
    temp = multiply(N, temp, temp)
    if (b % 2 == 1L)
        temp = multiply(N, temp, m)
    return temp
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toInt()
    val B = stringTokenizer.nextToken().toLong()
    val m: Array<Array<Int>> = Array(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            m[y][x] = row[x] % 1000
        }
    }
    val result = pow(N, m, B)
    writer.write(result.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}