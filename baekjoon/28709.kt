package `28709`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun check(s: String): Boolean {
    var open = s.count { it == '(' }
    var close = s.count { it == ')' }

    if (s.length % 2 != 0)
        return false
    if (open > s.length / 2 || close > s.length / 2)
        return false
    var state = 0
    var c = '.'
    for (i in s.indices) {
        c = s[i]
        if (c == '?') {
            if (open < s.length / 2) {
                open++
                c = '('
            } else {
                c = ')'
                close++
            }
        }
        if (c == '(') {
            state++
        } else {
            if (state == 0)
                return false
            state--
        }
    }
    return state == 0
}

fun checkChildCardV(s: String): Boolean {
    var state = 0
    for (c in s) {
        if (c == '(')
            state++
        else if (c == ')') {
            if (state <= 0)
                return false
            state--
        }
    }
    return state >= 0
}

fun reverse(s: String): String {
    val sb = StringBuilder()
    for (c in s.reversed()) {
        val rc = when (c) {
            ')' -> '('
            '(' -> ')'
            else -> '?'
        }
        sb.append(rc)
    }
    return sb.toString()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val s = reader.readLine()
        val hasWildCard = s.contains('*')
        var ans = false
        if (hasWildCard) {
            val splited = s.split("*")
            ans = checkChildCardV(splited.first().replace("?", "(")) &&
                    checkChildCardV(reverse(splited.last()).replace("?", "("))
        } else {
            ans = check(s)
        }
        if (ans)
            writer.write("YES\n")
        else
            writer.write("NO\n")
    }
    writer.flush()
}