package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun battle(a: Char, b: Char): Int {
    if (a == b)
        return 0
    return if (a == 'R') {
        if (b == 'P')
            return -1
        else
            return 1
    } else if (a == 'P') {
        if (b == 'S')
            return -1
        else
            return 1
    } else {
        if (b == 'R')
            return -1
        else
            return 1
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val first = reader.readLine()[0]
    val lighter = reader.readLine()
    val round = Array<Char>(lighter.length) { ' ' }
    var ans = 0
    for (bit in 1 until (1 shl lighter.length)) {
        var idx = 0
        for (i in 0 until lighter.length) {
            if (bit and (1 shl i) > 0)
                round[idx++] = lighter[i]
        }
        var win = false
        var valid = true
        for (i in 0 until idx) {
            val cur = round[i]
            val my = if (i == 0)
                first
            else
                round[i - 1]
            val battleResult = battle(my, cur)
            if (win && battleResult == 0) {
                valid = false
                break
            }
            win = battle(my, cur) == 1
        }
        if (valid)
            ans++
    }
    writer.write("${ans}\n")
    writer.flush()
}
    