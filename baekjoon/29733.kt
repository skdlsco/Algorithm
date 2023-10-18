package `29733`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (R, C, H) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Array<Char>>>(H) { Array(R) { Array(C) { '.' } } }
    for (z in 0 until H) {
        for (y in 0 until R) {
            val row = reader.readLine()
            for (x in 0 until C) {
                map[z][y][x] = row[x]
            }
        }
    }
    val arr = Array<Array<Array<Char>>>(H) { Array(R) { Array(C) { '.' } } }
    for (z in 0 until H) {
        for (y in 0 until R) {
            for (x in 0 until C) {
                var cnt = 0
                for (dz in -1..1) {
                    for (dy in -1..1) {
                        for (dx in -1..1) {
                            if (z + dz in 0 until H &&
                                y + dy in 0 until R &&
                                x + dx in 0 until C &&
                                map[z + dz][y + dy][x + dx] == '*'
                            ) {
                                cnt++
                            }
                        }
                    }
                }
                if (map[z][y][x] == '*')
                    arr[z][y][x] = '*'
                else
                    arr[z][y][x] = (cnt % 10).digitToChar()
            }
        }
    }
    for (z in 0 until H) {
        for (y in 0 until R) {
            for (x in 0 until C) {
                writer.write("${arr[z][y][x]}")
            }
            writer.newLine()
        }
    }
    writer.flush()
}
    