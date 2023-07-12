package `15926`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val line = reader.readLine()
    var result = 0
    // (), len
    val st = Stack<Pair<Boolean, Int>>()
    for (i in 0 until N) {
        if (line[i] == '(')
            st.add(Pair(true, 0))
        else {
            var sum = 0
            var flag = false
            while (st.isNotEmpty()) {
                val (t, len) = st.peek()
                if (t) {
                    if (!flag) {
                        sum += 2
                        flag = true
                    } else
                        break
                } else {
                    sum += len
                }
                st.pop()
            }
            if (!flag) {
                while (st.isNotEmpty())
                    st.pop()
                sum = 0
            }
            result = max(result, sum)
            st.add(Pair(false, sum))
        }
    }
    writer.write("${result}\n")
    writer.flush()
}