package clear

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val str = reader.readLine() // 10 26
    var sum = 1

    str.forEachIndexed { index, c ->
        sum *= if (c == 'd') 10 - if (index > 0 && str[index - 1] == 'd') 1 else 0
        else 26 - if (index > 0 && str[index - 1] != 'd') 1 else 0
    }
    println(sum)
}