package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val input = reader.readLine().split(" ")
    val N = input[0].toInt()
    val target = input[1]
    var cnt = 0
    repeat(N) {
        val temp = reader.readLine().split(" ")
        val w = temp[1].toInt()
        if (temp[0].split("_").contains(target))
            cnt += w
    }
    writer.write("${cnt}\n")
    writer.flush()
}