package `20327`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun <T> flipVertical(len: Int, arr: Array<Array<T>>) {
    for (i in 0 until len / 2) {
        for (x in 0 until len) {
            val temp = arr[i][x]
            arr[i][x] = arr[len - i - 1][x]
            arr[len - i - 1][x] = temp
        }
    }
}

fun <T> flipHorizontal(len: Int, arr: Array<Array<T>>) {
    for (i in 0 until len / 2) {
        for (y in 0 until len) {
            val temp = arr[y][i]
            arr[y][i] = arr[y][len - i - 1]
            arr[y][len - i - 1] = temp
        }
    }
}

fun <T> rotateRight(len: Int, arr: Array<Array<T>>) {
    val newArr = Array<ArrayList<T>>(len) { ArrayList() }
    for (y in 0 until len) {
        for (x in 0 until len) {
            newArr[y].add(arr[len - 1 - x][y])
        }
    }
    for (y in 0 until len) {
        for (x in 0 until len) {
            arr[y][x] = newArr[y][x]
        }
    }
}

fun <T> rotateLeft(len: Int, arr: Array<Array<T>>) {
    val newArr = Array<ArrayList<T>>(len) { ArrayList() }
    for (y in 0 until len) {
        for (x in 0 until len) {
            newArr[y].add(arr[x][len - 1 - y])
        }
    }
    for (y in 0 until len) {
        for (x in 0 until len) {
            arr[y][x] = newArr[y][x]
        }
    }
}

fun <T> doCommand(command: Int, len: Int, arr: Array<Array<T>>) {
    when (command) {
        1 -> flipVertical(len, arr)
        2 -> flipHorizontal(len, arr)
        3 -> rotateRight(len, arr)
        4 -> rotateLeft(len, arr)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, R) = reader.readLine().split(" ").map { it.toInt() }
    val len = 1 shl N
    val arr = Array<Array<Int>>(len) { Array(len) { 0 } }

    for (y in 0 until len) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until len) {
            arr[y][x] = line[x]
        }
    }

    repeat(R) {
        val (k, l) = reader.readLine().split(" ").map { it.toInt() }
        val subLen = 1 shl l
        val parentLen = len shr l
        val newArr = Array(parentLen) { Array(parentLen) { Array(subLen) { Array(subLen) { 0 } } } }
        for (py in 0 until parentLen) {
            for (px in 0 until parentLen) {
                for (cy in 0 until subLen) {
                    for (cx in 0 until subLen) {
                        newArr[py][px][cy][cx] = arr[(py * subLen) + cy][(px * subLen) + cx]
                    }
                }
            }
        }
        if (k < 5) {
            for (py in 0 until parentLen) {
                for (px in 0 until parentLen) {
                    doCommand(k, subLen, newArr[py][px])
                }
            }
        } else
            doCommand(k - 4, parentLen, newArr)
        for (py in 0 until parentLen) {
            for (px in 0 until parentLen) {
                for (cy in 0 until subLen) {
                    for (cx in 0 until subLen) {
                        arr[(py * subLen) + cy][(px * subLen) + cx] = newArr[py][px][cy][cx]
                    }
                }
            }
        }
    }
    for (y in 0 until len) {
        for (x in 0 until len) {
            writer.write("${arr[y][x]} ")
        }
        writer.newLine()
    }
    writer.flush()
}