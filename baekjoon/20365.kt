val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun check(s: String, first: Char, second: Char): Int {
    var count = if (s.contains(first)) 1 else 0
    var prev = first
    for (c in s) {
        if (c != prev && c == second)
            count++
        prev = c
    }
    if (prev == second)
        count++
    return count
}

fun main() {
    val N = reader.readLine().toInt()
    val s = reader.readLine()

    writer.write("${minOf(check(s, 'R', 'B'), check(s, 'B', 'R'))}\n")
    writer.flush()
}