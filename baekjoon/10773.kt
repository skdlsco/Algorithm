package `10773`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val K = reader.readLine().toInt()
    val st = Stack<Int>()
    repeat(K) {
        val n = reader.readLine().toInt()
        if (n == 0)
            st.pop()
        else st.push(n)
    }
    var sum = 0L
    for (n in st) {
        sum += n
    }
    writer.write("${sum}\n")
    writer.flush()
}