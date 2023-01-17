package clear

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    (1..n).reversed().forEach {
        (1..it).forEach {
            print("*")
        }
        println()
    }
}