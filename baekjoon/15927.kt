package `15927`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val str = reader.readLine()
    if (str.all { it == str[0] })
        writer.write("-1\n")
    else {
        val reversed = str.reversed()
        val isPalindrome = (str.indices).all { reversed[it] == str[it] }
        if (isPalindrome)
            writer.write("${str.length - 1}\n")
        else
            writer.write("${str.length}\n")
    }
    writer.flush()
}
    