package `2309`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = Array<Int>(9) { 0 }

    repeat(9) {
        arr[it] = scanner.nextInt()
    }
    val sum = arr.sum()
    arr.sort()
    (0 until 9).forEach firstForEach@{ first ->
        (0 until 9).forEach secondFroEach@{ second ->
            if (first != second && sum - arr[first] - arr[second] == 100) {
                arr.filterIndexed { index, i -> index != first  && index != second }.forEach {
                    println(it)
                }
                return
            }
        }
    }
}