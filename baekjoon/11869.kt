fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()
    val N = reader.readLine().toInt()
    val stones = reader.readLine().split(" ").map { it.toLong() }
    var result = 0L
    stones.forEach { result = result xor it }
    if (result == 0L) {
        writer.write("cubelover")
    } else {
        writer.write("koosaga")
    }
    writer.flush()
}