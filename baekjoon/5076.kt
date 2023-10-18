package `5076`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    var line = reader.readLine()
    val tagNameEndChars = charArrayOf(' ', '/', '>')
    while (line != "#") {
        val stack = Stack<String>()
        var isLegal = true
        var idx = 0
        while (idx < line.length && idx != -1) {
            var tagName = ""
            var isCloseTag = false
            // startIdx
            idx = line.indexOf('<', idx)
            if (idx == -1)
                break
            if (idx + 1 < line.length && line[idx + 1] == '/') {
                idx++
                isCloseTag = true
            }
            val tagNameStartIdx = idx
            val tagNameEndIdx = line.indexOfAny(tagNameEndChars, idx + 1)
            if (tagNameEndIdx == -1) {
                isLegal = false
                break
            }
            tagName = line.slice(tagNameStartIdx + 1 until tagNameEndIdx)
            idx = line.indexOf('>', idx)
            if (idx == -1) {
                isLegal = false
                break
            }
            if (line[idx - 1] == '/') {
                if (isCloseTag) {
                    isLegal = false
                    break
                }
            } else {
                if (isCloseTag) {
                    if (stack.isEmpty() || stack.pop() != tagName) {
                        isLegal = false
                        break
                    }
                } else {
                    stack.push(tagName)
                }
            }
        }
        if (stack.isNotEmpty())
            isLegal = false
        if (isLegal)
            writer.write("legal\n")
        else
            writer.write("illegal\n")
        line = reader.readLine()
    }
    writer.flush()
    writer.close()
}