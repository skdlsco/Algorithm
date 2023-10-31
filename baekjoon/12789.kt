import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val st = Stack<Int>()
    var cur = 1
    for (i in 0 until n) {
        if (arr[i] != cur)
            st.add(arr[i])
        else
            cur++
        while (st.isNotEmpty() && st.peek() == cur) {
            st.pop()
            cur++
        }
    }
    while (st.isNotEmpty() && st.peek() == cur) {
        st.pop()
        cur++
    }
    if (st.isEmpty())
        writer.write("Nice")
    else
        writer.write("Sad")
    writer.flush()
}
