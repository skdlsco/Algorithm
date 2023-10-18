package `1012`

import java.util.*

fun clearLand(arr: Array<Array<Boolean>>, x: Int, y: Int) {
    if (!arr[y][x])
        return
    arr[y][x] = false
    if (arr[0].size > x + 1)
        clearLand(arr, x + 1, y)
    if (x > 0)
        clearLand(arr, x - 1, y)
    if (arr.size > y + 1)
        clearLand(arr, x, y + 1)
    if (y > 0)
        clearLand(arr, x, y - 1)
}

fun find(arr: Array<Array<Boolean>>): Int {
    var cnt = 0
    arr.forEachIndexed { y, xs ->
        xs.forEachIndexed { x, b ->
            if (b) {
                clearLand(arr, x, y)
                cnt++
            }
        }
    }
    return cnt
}

fun input(scanner: Scanner): Array<Array<Boolean>> {
    val M = scanner.nextInt()
    val N = scanner.nextInt()
    val cnt = scanner.nextInt()

    val arr = Array<Array<Boolean>>(N) { Array(M) { false } }

    repeat(cnt) {
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        arr[y][x] = true
    }
    return arr
}

fun main() {
    val scanner = Scanner(System.`in`)

    repeat(scanner.nextInt()) {
        val result = find(input(scanner))
        println(result)
    }
}