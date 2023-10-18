package `1157`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val s = scanner.next().toUpperCase()
    val alphaArr = ('A'..'Z').toList()
    val arr = alphaArr.map { c ->
        s.count { it == c }
    }
    val maxValue = arr.maxOrNull()
    val result = arr.filter { it == maxValue }
    if (result.size > 1)
        println("?")
    else
        println(alphaArr[arr.indexOf(maxValue)])

}