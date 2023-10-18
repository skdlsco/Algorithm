package `2239`

import java.io.BufferedReader
import java.io.InputStreamReader

fun getNum(sudoku: Array<Array<Int>>, pos: Pair<Int, Int>): List<Int> {
    val nums = Array<Int>(9) { it + 1 }

    return nums.filter { num ->
        sudoku.forEachIndexed { y, it ->
            if (sudoku[y][pos.first] == num)
                return@filter false
            it.forEachIndexed { x, i ->
                if (sudoku[pos.second][x] == num)
                    return@filter false
                if (y / 3 == pos.second / 3 && x / 3 == pos.first / 3 && sudoku[y][x] == num)
                    return@filter false
            }
        }
        true
    }
}

fun getResult(sudoku: Array<Array<Int>>, empty: ArrayList<Pair<Int, Int>>, size: Int, idx: Int): Boolean {
    if (idx == size) {
        sudoku.forEach {
            it.forEach {
                print("$it")
            }
            println()
        }
        return true
    }
    getNum(sudoku, empty[idx]).forEach { num ->
        sudoku[empty[idx].second][empty[idx].first] = num
        if (getResult(sudoku, empty, size, idx + 1))
            return true
        (idx until size).forEach {
            sudoku[empty[it].second][empty[it].first] = 0
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val sudoku = Array<Array<Int>>(9) { Array<Int>(9) { 0 } }
    val empty = ArrayList<Pair<Int, Int>>()
    for (y in 0 until 9) {
        val line = reader.readLine()
        for (x in 0 until 9) {
            val cell = line[x].digitToInt()
            sudoku[y][x] = cell
            if (cell == 0)
                empty.add(Pair(x, y))
        }
    }
    getResult(sudoku, empty, empty.size, 0)
}