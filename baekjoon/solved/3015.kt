package `3015`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val st = Stack<Pair<Int, Int>>()
    var sum = 0L
    repeat(N) {
        val cur = reader.readLine().toInt()
        var sameCnt = 1
        while (st.isNotEmpty() && st.last().first < cur) {
            sum += st.last().second
            st.pop()
        }
        if (st.isNotEmpty() && st.last().first == cur) {
            sum += st.last().second
            sameCnt += st.last().second
            st.pop()
        }
        if (st.isNotEmpty())
            sum++
        st.push(Pair(cur, sameCnt))
    }
    writer.write("${sum}\n")
    writer.flush()
}