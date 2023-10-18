package `10809`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val word = scanner.next()
    val result = IntArray('z' - 'a' + 1) { -1 }
    word.forEachIndexed { index: Int, c: Char ->
        if (result[c - 'a'] == -1)
            result[c - 'a'] = index
    }
    println(result.joinToString(" ") { it.toString() })
}