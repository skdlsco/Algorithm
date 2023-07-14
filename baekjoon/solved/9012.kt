package `9012`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val st = Stack<Char>()
        val line = reader.readLine()
        var isValid = true
        for (c in line) {
            if (c == '(')
                st.push(c)
            else {
                if (st.isEmpty() || st.peek() != '(') {
                    isValid = false
                    break
                }
                st.pop()
            }
        }
        if (st.isNotEmpty())
            isValid = false
        if (isValid)
            writer.write("YES")
        else
            writer.write("NO")
        writer.newLine()
    }
    writer.flush()
}