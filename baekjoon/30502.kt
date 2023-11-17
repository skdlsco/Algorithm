package `B`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arrP = Array<Int>(N + 1) { -1 }
    val arrM = Array<Int>(N + 1) { -1 }
    repeat(M) {
        val st = StringTokenizer(reader.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken()
        val c = st.nextToken().toInt()
        if (b == "P") {
            arrP[a] = c
        } else {
            arrM[a] = c
        }
    }
    val min = (1..N).count {
        arrP[it] == 1 && arrM[it] == 0
    }
    val max = (1..N).count {
        arrP[it] != 0 && arrM[it] != 1
    }
    writer.write("${min} ${max}\n")
    writer.flush()
}
    