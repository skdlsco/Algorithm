package `28064`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val names = Array<String>(N) { reader.readLine() }
    var cnt = 0

    for (i in 0 until N - 1) {
        for (j in i + 1 until N) {
            if (isConnectable(names[i], names[j]))
                cnt++
        }
    }
    writer.write("${cnt}")
    writer.flush()
}

fun isConnectable(a: String, b: String): Boolean {
    val longer = if (b.length > a.length) b else a
    val shorter = if (a.length < b.length) a else b
    for (len in shorter.indices) {
        if ((0 .. len).all { longer[longer.lastIndex - len + it] == shorter[it] })
            return true
    }
    for (len in shorter.indices) {
        if ((0 .. len).all { longer[it] == shorter[shorter.lastIndex - len + it] })
            return true
    }
    return false
}
