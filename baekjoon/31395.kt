package `B`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var prev = 0
    var cnt = 0
    var ans = 0L
    for (i in arr) {
        if (prev < i)
            cnt++
        else
            cnt = 1
        ans += cnt
        prev = i
    }
    writer.write("${ans}\n")
    writer.flush()
}
    