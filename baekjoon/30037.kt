package `30037`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    repeat(N) {
        var line = reader.readLine()
        var input = line.split(" ")
        val arr = ArrayList<String>()
        for (word in input) {
            if ((word == "Korea" || (word.startsWith("Korea") && word.length == 6 &&  "!?,.".contains(word.last()))) &&
                arr.size >= 2 && arr.last() == "of" && arr[arr.size - 2].none {
                    "!?,.".contains(
                        it
                    )
                }) {
                arr.removeLast()
                var w = arr.removeLast()
                if (w.first().isLowerCase()) {
                    w = w.first().uppercase() + w.slice(1..w.lastIndex)
                }
                w = "K-" + w
                if (word.length == 6)
                    w += word.last()
                arr.add(w)
            } else
                arr.add(word)
        }
        line = arr.joinToString(" ")
        arr.clear()
//         reverse!
        input = line.split(" ").reversed()
        for (word in input) {
            if (word == "Korea" && arr.size > 0) {
                var w = arr.removeLast()
                if (w.first().isLowerCase()) {
                    w = w.first().uppercase() + w.slice(1..w.lastIndex)
                }
                arr.add("K-" + w)
            } else
                arr.add(word)
        }
        arr.reverse()
        line = arr.joinToString(" ")
        writer.write(line)
        writer.newLine()
    }
    writer.flush()
}
    