package `9421`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun checkSang(sang: Array<Int>, now: Int): Int {
    if (sang[now] != 0)
        return sang[now]
    var cur = now
    var next = 0
    while (cur > 0) {
        next += (cur % 10) * (cur % 10)
        cur /= 10
    }
    sang[now] = next
    val result = checkSang(sang, next)
    sang[now] = result
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val sang = Array<Int>(1000001) { 0 }
    for (i in 1 until 1000001) {
        checkSang(sang, i)
    }
    val prime = Array<Boolean>(1000001) { true }
    prime[0] = false
    prime[1] = false
    for (i in 2 until 1001) {
        if (prime[i]) {
            for (j in i * i until 1000001 step i)
                prime[j] = false
        }
    }
    val N = reader.readLine().toInt()
    for (i in 0..N) {
        if (sang[i] == 1 && prime[i])
            writer.write("${i}\n")
    }
    writer.flush()
}