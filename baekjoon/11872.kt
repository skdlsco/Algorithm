package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()
fun main() {
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }

    val ans = arr.fold(0L) { acc, l ->
        acc xor if (l % 4 == 0L) l - 1 else if (l % 4 == 3L) l + 1 else l
    }
    if (ans > 0)
        writer.write("koosaga")
    else
        writer.write("cubelover")
    writer.flush()
}