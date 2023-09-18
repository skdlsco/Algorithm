package `29734`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toLong() }
    val (T, S) = reader.readLine().split(" ").map { it.toLong() }
    val inHomeCnt = (N / 8L + if (N % 8L != 0L) 1 else 0)
    val inHome = inHomeCnt * 8 + (inHomeCnt - 1) * S -
            if (N % 8L != 0L) 8 - N % 8 else 0
    val inDokCnt = (M / 8L + if (M % 8L != 0L) 1 else 0)
    val inDok = inDokCnt * 8 + (inDokCnt * T) + (inDokCnt - 1) * (S + T) -
            if (M % 8L != 0L) 8 - M % 8 else 0
    if (inHome < inDok)
        writer.write("Zip\n${inHome}\n")
    else
        writer.write("Dok\n${inDok}\n")
    writer.flush()
}
    