package `4949`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val st = Stack<Char>()
        var isValid = true
        val line = reader.readLine()
        if (line == ".")
            break
        for (c in line) {
            if (c == '[' || c == '(')
                st.push(c)
            if (c == ']') {
                if (st.isEmpty() || st.peek() != '[') {
                    isValid = false
                    break
                }
                st.pop()
            }
            if (c == ')') {
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
            writer.write("yes")
        else
            writer.write("no")
        writer.newLine()
    }
    writer.flush()
}