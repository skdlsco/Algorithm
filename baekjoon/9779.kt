val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    reader.lines()
            .forEach {
                if (Regex("da+dd?(i|y)").matches(it))
                    writer.write("She called me!!!\n")
                else
                    writer.write("Cooing\n")
            }
    writer.flush()
}