package `1259`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var num = reader.readLine()
    while (num != "0") {
        val reversed = num.reversed()
        var isPalindrome = true
        for (i in num.indices) {
            if (num[i] != reversed[i])
                isPalindrome =false
        }
        if (isPalindrome)
            writer.write("yes")
        else
            writer.write("no")
        writer.newLine()
        num = reader.readLine()
    }
    writer.flush()
}