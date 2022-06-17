package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val case = scanner.nextInt()
    (0 until case).forEach { _ ->
        val quizResult = scanner.next()
        var bonus = 0
        val score = quizResult.map { it
            if (it == 'O')
                bonus++
            else
                bonus = 0
            bonus
        }.sum()
        println(score)
    }
}