package `Main`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val T = reader.readLine().toInt()
    repeat(T) {
        val line = reader.readLine()
        var prefix = 0
        var suffix = 0
        var n = 0
        var check = false
        for (c in line) {
            if (c in "01") {
                n = c.digitToInt()
                check = true
            } else
            if (check) {
                suffix++
            } else {
                prefix++
            }
        }
        if (suffix > 0)
            n = 1
        writer.write("${(prefix % 2) xor n}\n")
    }

    writer.flush()
}