package `1541`

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()
    val arr = ArrayList<Any>()

    var buffer = ""
    input.forEach {
        if (it in "-+") {
            if (buffer.isNotBlank()) {
                arr.add(buffer.toInt())
                buffer = ""
            }
            arr.add(it)
        } else {
            buffer += it
        }
    }
    if (buffer.isNotBlank())
        arr.add(buffer.toInt())
    var sum = 0
    var isMinus = false
    arr.forEach {
        when (it) {
            '-' -> isMinus = true
            is Int -> sum += if (isMinus) -it else it
        }
    }
    println(sum)
}