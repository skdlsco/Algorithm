package `13140`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun helloWorld(target: Int, used: Array<Boolean>, hwdelor: Array<Int>, depth: Int): Boolean {
    if (depth == 7) {
        val hello = hwdelor[0] * 10000 + hwdelor[3] * 1000 + hwdelor[4] * 110 + hwdelor[5]
        val world = hwdelor[1] * 10000 + hwdelor[5] * 1000 + hwdelor[6] * 100 + hwdelor[4] * 10 + hwdelor[2]
        if (target == hello + world) {
            println("  ${hello}")
            println("+ ${world}")
            println("-------")
            println("%7d".format(hello + world))
            return true
        }
        return false
    }
    for (i in 0 until 10) {
        if (!used[i]) {
            if (depth < 2 && i == 0)
                continue
            used[i] = true
            hwdelor[depth] = i
            if (helloWorld(target, used, hwdelor, depth + 1))
                return true
            used[i] = false
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val used = Array<Boolean>(10) { false }
    val hwdelor = Array<Int>(7) { 0 }
    if (!helloWorld(N, used, hwdelor, 0))
        println("No Answer")
}