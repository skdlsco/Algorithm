package `1924`

import java.util.Scanner

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val day = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
    val month = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val x = scanner.nextInt() - 1
    val y = scanner.nextInt()

    val dayIndex = (month.filterIndexed { index, i -> x > index }.sum() + y) % 7
    print(day[dayIndex])
}