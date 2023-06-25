package `10775`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class TokenReader(private val reader: BufferedReader) {
    private var tokenizer = StringTokenizer(reader.readLine())

    fun nextToken(): String {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = StringTokenizer(reader.readLine())
        }
        return tokenizer.nextToken()
    }

    fun nextInt(): Int {
        return nextToken().toInt()
    }
}

fun getAndUpdateGate(gates: Array<Int>, cur: Int): Int {
    if (cur == 0)
        return 0
    if (gates[cur] == cur) {
        gates[cur] = gates[cur - 1]
        return cur
    }
    val next = getAndUpdateGate(gates, gates[cur])
    gates[cur] = next
    return next
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val tReader = TokenReader(reader)
    val G = tReader.nextInt()
    val P = tReader.nextInt()
    var cnt = 0
    val gates = Array<Int>(G + 1) { it }
    for (i in 0 until P) {
        val gi = tReader.nextInt()
        val gate = getAndUpdateGate(gates, gi)
        if (gate == 0)
            break
        cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}

