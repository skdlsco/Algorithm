package `2035`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var minString: String = ""

fun find(S: String, idx: Int, prev: String) {
    if (idx == S.length) {
        if (minString.isBlank())
            minString = prev
        else if (prev.length < minString.length || prev.length == minString.length && prev < minString)
            minString = prev
    }
    var start = idx
    while (start < S.length && S[start] == '0')
        start++
    for (i in start until S.length) {
        val curLength = i - start + 1
        if (curLength < prev.length)
            continue
        if (minString.isNotBlank() && minString.length < curLength)
            break
        val cur = S.slice(start..i)
        if (cur.length > prev.length || cur.length == prev.length && cur > prev) {
            find(S, i + 1, cur)
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val S = reader.readLine()
    find(S, 0, "")
    writer.write(minString)
    writer.flush()
}
    