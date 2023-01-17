package clear

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val num = scanner.nextLine().split(" ").sumOf { it.toInt() * it.toInt() } % 10

    println(num)
}