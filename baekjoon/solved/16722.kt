package `16722`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val card = Array<Array<String>>(9) { Array(3) { "" } }
val board = Array<Array<Array<Boolean>>>(9) { Array(9) { Array(9) { false } } }

fun isHap(a: Int, b: Int, c: Int): Boolean {
    val set = HashSet<String>()
    return (0 until 3).all {
        set.clear()
        set.add(card[a][it])
        set.add(card[b][it])
        set.add(card[c][it])
        set.size == 1 || set.size == 3
    }
}

fun combo(selected: Array<Int>, depth: Int) {
    if (depth == 3) {
        board[selected[0]][selected[1]][selected[2]] = isHap(selected[0], selected[1], selected[2])
        return
    }
    val start = if (depth == 0) 0 else selected[depth - 1] + 1
    for (i in start until 9) {
        selected[depth] = i
        combo(selected, depth + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    repeat(9) {
        card[it] = reader.readLine().split(" ").toTypedArray()
    }
    combo(arrayOf(0, 0, 0), 0)
    var total = board.sumOf { it.sumOf { it.count { it } } }
    val N = reader.readLine().toInt()
    var score = 0
    repeat(N) {
        val tokenizer = StringTokenizer(reader.readLine())
        val command = tokenizer.nextToken()
        if (command == "H") {
            val arr = arrayOf(
                tokenizer.nextToken().toInt() - 1,
                tokenizer.nextToken().toInt() - 1,
                tokenizer.nextToken().toInt() - 1
            ).sorted()
            if (board[arr[0]][arr[1]][arr[2]]) {
                score++
                board[arr[0]][arr[1]][arr[2]] = false
                total--
            } else
                score--
        } else {
            if (total == 0) {
                score += 3
                total--
            } else
                score--
        }
    }
    writer.write("${score}\n")
    writer.flush()
}