package `29737`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Streak(var length: Int, var useFreeze: Int, var start: Int, var pending: Int) {
    operator fun compareTo(other: Streak): Int {
        return if (this.length != other.length)
            this.length - other.length
        else if (this.useFreeze != other.useFreeze)
            other.useFreeze - this.useFreeze
        else if (this.start != other.start)
            other.start - this.start
        else 0
    }

    override fun equals(other: Any?): Boolean {
        if (other is Streak) {
            return length == other.length && useFreeze == other.useFreeze && start == other.start
        }
        return false
    }
}

data class Person(
    var name: String,
    var failed: Int = 0,
    var bestStreak: Streak = Streak(0, 0, 0, 0)
) : Comparable<Person> {
    override operator fun compareTo(other: Person): Int {
        return if (this.bestStreak != other.bestStreak)
            this.bestStreak.compareTo(other.bestStreak)
        else if (this.failed != other.failed)
            other.failed - this.failed
        else
            other.name.compareTo(name)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, W) = reader.readLine().split(" ").map { it.toInt() }
    val friends = ArrayList<Person>()
    repeat(N) {
        val name = reader.readLine()
        val board = Array<Char>(7 * W) { '.' }
        for (i in 0 until 7) {
            val line = reader.readLine()
            for (j in 0 until W) {
                board[j * 7 + i] = line[j]
            }
        }
        val p = Person(name, board.count { it == 'X' })
        var streak = Streak(0, 0, 0, 0)
        for (i in board.indices) {
            val c = board[i]
            if (c == 'F') {
                if (streak.length != 0)
                    streak.pending++
            }
            if (c == 'X') {
                if (p.bestStreak < streak)
                    p.bestStreak = streak
                streak = Streak(0, 0, 0, 0)
            }
            if (c == 'O') {
                if (streak.length != 0) {
                    streak.length++
                    streak.useFreeze += streak.pending
                    streak.pending = 0
                } else {
                    streak = Streak(1, 0, i, 0)
                }
            }
        }
        if (p.bestStreak < streak)
            p.bestStreak = streak
        friends.add(p)
    }
    friends.sortByDescending { it }
    var rank = 0
    var prev: Person? = null
    for (i in 0 until N) {
        if (prev != null && prev.bestStreak == friends[i].bestStreak &&
            prev.failed == friends[i].failed
        ) {
            writer.write("${rank}. ${friends[i].name}\n")
        } else {
            rank++
            writer.write("${rank}. ${friends[i].name}\n")
        }
        prev = friends[i]
    }
    writer.flush()
}
    