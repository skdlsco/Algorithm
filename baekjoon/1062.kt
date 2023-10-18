package `1062`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun dfs(words: Array<String>, using: Array<Boolean>, K: Int, pos: Int, depth: Int): Int {
    if (pos >= 26)
        return 0
    if (depth == K) {
        return words.count {
            it.all {
                using[it.code - 'a'.code]
            }
        }
    }
    var result = 0
    for (i in pos until 26) {
        if (using[i])
            continue
        using[i] = true
        result = max(result, dfs(words, using, K, i, depth + 1))
        using[i] = false
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val words = Array<String>(N) { reader.readLine() }
    val using = Array<Boolean>(26) { false }
    using[0] = true
    using[2] = true
    using[8] = true
    using[13] = true
    using[19] = true
    val result = dfs(words, using, K - 5, 0, 0)
    writer.write("${result}\n")
    writer.flush()
}