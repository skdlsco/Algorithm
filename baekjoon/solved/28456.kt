package `28456`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun query1(N: Int, arr: Array<Array<Int>>, y: Int) {
    val temp = arr[y].last()
    for (x in N - 1 downTo 1) {
        arr[y][x] = arr[y][x - 1]
    }
    arr[y][0] = temp
}

fun query2(N: Int, arr: Array<Array<Int>>) {
    val newArr = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        for (x in 0 until N) {
            newArr[x][N - y - 1] = arr[y][x]
        }
    }
    for (y in 0 until N) {
        for (x in 0 until N) {
            arr[y][x] = newArr[y][x]
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            arr[y][x] = row[x]
        }
    }
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val tokenizer = StringTokenizer(reader.readLine())
        val query = tokenizer.nextToken().toInt()
        if (query == 1) {
            query1(N, arr, tokenizer.nextToken().toInt() - 1)
        } else {
            query2(N, arr)
        }
    }
    writer.write(arr.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}