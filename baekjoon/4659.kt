package clear

import java.util.*

fun String.print(isAcceptable: Boolean) {
    println("<$this> is ${if (isAcceptable) "" else "not "}acceptable.")
}

fun main() {
    val scanner = Scanner(System.`in`)
    var password: String = scanner.next()
    val vowel = "aeiou"

    while (password != "end") {
        var error = false
        var isOnlyConsonant = true
        var prev = ' '
        var cnt = 0
        password.forEach {
            if (prev == it && prev != 'e' && prev != 'o')
                error = true
            if (prev in vowel && it in vowel)
                cnt++
            else if (prev !in vowel && it !in vowel)
                cnt++
            else
                cnt = 1
            if (cnt == 3)
                error = true
            if (it in vowel)
                isOnlyConsonant = false
            prev = it
        }
        error = isOnlyConsonant || error
        password.print(!error)
        password = scanner.next()
    }
}