package `29724`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    var weight = 0L
    var appleCnt = 0L
    repeat(N) {
        val st = StringTokenizer(reader.readLine())
        val T = st.nextToken()
        val W = st.nextToken().toInt() / 12
        val H = st.nextToken().toInt() / 12
        val L = st.nextToken().toInt() / 12
        if (T == "A") {
            appleCnt += W * H * L
            weight += 1000
        } else
            weight += 6000
    }
    writer.write("${weight + appleCnt * 500}\n")
    writer.write("${appleCnt * 4000}\n")
    writer.flush()
}
    