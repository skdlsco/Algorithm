package clear

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val c = scanner.nextInt()
    val students = arrayListOf<Int>()
    repeat(c) {
        val n = scanner.nextInt()
        var sum = 0
        students.clear()
        repeat(n) {
            val a = scanner.nextInt()
            sum += a
            students.add(a)
        }
        val aver = sum / n.toFloat()
        println(Formatter().format("%.3f%%", students.filter { it > aver }.size / n.toFloat() * 100))
    }
}