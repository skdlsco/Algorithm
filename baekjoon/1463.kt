package clear

import java.util.*
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = Array<Int>(1000001) { 0 }
    val N = scanner.nextInt()
    (2 until arr.size).forEach {
        arr[it] = arr[it - 1]
        if (it % 3 == 0)
            arr[it] = min(arr[it], arr[it / 3])
        if (it % 2 == 0)
            arr[it] = min(arr[it], arr[it / 2])
        arr[it]++
    }
    println(arr[N])
}