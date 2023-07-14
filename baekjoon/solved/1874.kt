package `1874`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    var p = 1
    val st = Stack<Int>()
    var isPossible = true
    val sb = StringBuilder()
    for (i in 1..n) {
        val target = reader.readLine().toInt()
        if (st.isNotEmpty() && st.peek() > target) {
            isPossible = false
            break
        }
        while (p <= target) {
            st.add(p)
            sb.append("+\n")
            p++
        }
        sb.append("-\n")
        st.pop()
    }
    if (!isPossible)
        writer.write("NO")
    else
        writer.write(sb.toString())
    writer.flush()
}