package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    var (A, B) = reader.readLine().split(" ").map { it.toLong() }
    val (C, D) = reader.readLine().split(" ").map { it.toLong() }
    val K = reader.readLine().toInt()
    if (K == 0) {
        val cnt = A / B + if (A % B > 0) 1L else 0L
        val cnt2 = (A + C) / D + if ((A + C) % D > 0) 1L else 0L
        if (cnt < cnt2) {
            writer.write("${cnt}")
        } else {
            writer.write("-1")
        }
    } else {
        var success = false
        var day = 1
        var toka = 0L
        var dol = 0L
        while (true) {
            toka += B
            dol += D
            B -= K
            if (toka >= A) {
                success = dol < A + C
                break
            }
            if (B == 0L)
                break
            day++
        }
        if (success)
            writer.write("${day}")
        else
            writer.write("-1")
    }
    writer.flush()
}