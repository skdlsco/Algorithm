package `27884`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1000000007

fun calc(n: Int, m: Int, alreadyExist: Boolean, isGround: Boolean, depth: Int, len: Int): Long {
    val exits = if (isGround) 5L else 11L
    val isExist = alreadyExist || len == m
    if (depth == n) {
        return if (isExist) exits else 0L
    }
    if (!isExist && n - depth + len < m)
        return 0
    var result = (exits * calc(n, m, isExist, isGround, depth + 1, 1)) % MOD
    if (len < m)
        result += (exits * calc(n, m, isExist, !isGround, depth + 1, len + 1)) % MOD
    return result % MOD
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    val result = calc(n, m, false, true, 1, 1) + calc(n, m, false, false, 1, 1)
    writer.write("${result % MOD}\n")
    writer.flush()
}