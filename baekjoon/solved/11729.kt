package `11729`

import java.io.BufferedWriter
import java.io.OutputStreamWriter

val writer = BufferedWriter(OutputStreamWriter(System.out))

fun f(n: Int, start: Int, mid: Int, end: Int) {
    if (n == 1){
        writer.write("$start $end\n")
        return
    }
    f(n - 1, start, end, mid)
    f(1, start, mid, end)
    f(n - 1, mid, start,end)
}

fun main() {
    val N = readLine()!!.toInt()

    writer.write("${(1 shl N) - 1}\n")
    f(N, 1, 2, 3)
    writer.flush()
    writer.close()
}

