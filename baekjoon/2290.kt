package `2290`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

class Screen(val s: Int, val n: String) {
    val builder = StringBuilder()

    fun printHorizontal(key: String) {
        n.forEach { digit ->
            builder.append(" ")
            repeat(s) {
                if (digit in key)
                    builder.append("-")
                else
                    builder.append(" ")
            }
            builder.append("  ")
        }
        builder.append("\n")
    }

    fun printVertical(leftKey: String, rightKey: String) {
        repeat(s) {
            n.forEach { digit ->
                if (digit in leftKey)
                    builder.append("|")
                else
                    builder.append(" ")
                repeat(s) {
                    builder.append(" ")
                }
                if (digit in rightKey)
                    builder.append("|")
                else
                    builder.append(" ")
                builder.append(" ")
            }
            builder.append("\n")
        }
    }

    fun printNumber(): String {
        printHorizontal("23567890")
        printVertical("456890", "12347890")
        printHorizontal("2345689")
        printVertical("2680", "134567890")
        printHorizontal("2356890")
        return builder.toString()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val s = stringTokenizer.nextToken().toInt()
    val n = stringTokenizer.nextToken()

    writer.write(Screen(s, n).printNumber())
    writer.flush()
}