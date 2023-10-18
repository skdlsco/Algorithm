package `2841`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, P) = reader.readLine().split(" ").map { it.toInt() }
    val guitar = Array<Stack<Int>>(6) { Stack<Int>() }
    var cnt = 0
    repeat(N) {
        val (line, fret) = reader.readLine().split(" ").map { it.toInt() - 1 }
        while (guitar[line].isNotEmpty() && guitar[line].peek() > fret) {
            guitar[line].pop()
            cnt++
        }
        if (guitar[line].isEmpty()) {
            guitar[line].push(fret)
            cnt++
        } else if (guitar[line].peek() != fret){
            guitar[line].push(fret)
            cnt++
        }
    }
    println(cnt)
}