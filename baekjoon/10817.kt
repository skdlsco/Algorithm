package clear

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()

    if (a > b)
        if (a > c)
            if (b > c)
                print(b)
            else
                print(c)
        else
            print(a)
    else
        if (b > c)
            if (a > c)
                print(a)
            else
                print(c)
        else print(b)
}